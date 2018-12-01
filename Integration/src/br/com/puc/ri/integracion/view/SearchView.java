package br.com.puc.ri.integracion.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.swing.JFormattedTextField;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.chart.PieChartModel;

import br.com.puc.ri.integracion.controller.ColetaController;
import br.com.puc.ri.integracion.dto.IndicadorQualidade;
import br.com.puc.ri.integracion.dto.TagBrokenTotal;
import br.com.puc.ri.integracion.util.DecimalFormattedField;

@ManagedBean
@RequestScoped
public class SearchView implements Serializable {
	private static final long serialVersionUID = 4629817047379532658L;

	private String tipoResultado;
	private String tag;
	private Integer iteracao;
	private TagBrokenTotal brokenTotal;
	private PieChartModel pieChartModel;

	public void search() throws IOException {

		if (StringUtils.isBlank(tag)) {
			FacesContext.getCurrentInstance().addMessage("form:msg", new FacesMessage("Erro: É necessario ter uma hashtag para buscar."));
			return;
		}

		ColetaController coletaController = new ColetaController();
		TagBrokenTotal tagBrokenTotal = coletaController.coletaDadosIntegracoes(tag, iteracao, tipoResultado);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tagBrokenTotal", tagBrokenTotal);
		FacesContext.getCurrentInstance().getExternalContext().redirect("retorno.xhtml");
	}

	public String getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getIteracao() {
		return iteracao;
	}

	public void setIteracao(Integer iteracao) {
		this.iteracao = iteracao;
	}

	public TagBrokenTotal getBrokenTotal() {
		if (brokenTotal == null)
			return (TagBrokenTotal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tagBrokenTotal");
		return brokenTotal;
	}

	public void setBrokenTotal(TagBrokenTotal brokenTotal) {
		this.brokenTotal = brokenTotal;
	}

	public PieChartModel getPieChartModel() {

		if (brokenTotal == null) {
			brokenTotal = (TagBrokenTotal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tagBrokenTotal");
		}

		pieChartModel = new PieChartModel();
		Map<String, Number> indicadores = new HashMap<String, Number>();
		for (IndicadorQualidade indicador : brokenTotal.getIndicadoresMsg()) {

			JFormattedTextField porcentagem = new DecimalFormattedField(DecimalFormattedField.PORCENTAGEM);
			porcentagem.setValue(indicador.getPorcentagem());
			String p = porcentagem.getText();

			indicadores.put(indicador.getIndicador() + " - " + p, indicador.getQuantidade());
		}

		pieChartModel.setData(indicadores);
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}

}
