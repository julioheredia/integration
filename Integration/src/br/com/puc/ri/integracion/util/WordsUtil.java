package br.com.puc.ri.integracion.util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsUtil {

	public static final String[] STOPWORDS_GERAIS = { "t", "rt", "i", "http", "https", "np", "s", "m", "mt", "ll",
			"y", "n", "\n", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "o", "p", "q", "r", "t", "u", "v",
			"x", "w", "\\m" };

	public static final String[] STOPWORDS_PORTUGUES = { "a", "agora", "ainda", "alguém", "algum", "alguma", "algumas",
			"alguns", "ampla", "amplas", "amplo", "amplos", "ante", "antes", "ao", "aos", "após", "aquela",
			"aquelas", "aquele", "aqueles", "aquilo", "as", "até", "através", "cada", "coisa", "coisas", "com",
			"como", "contra", "contudo", "da", "daquele", "daqueles", "das", "de", "dela", "delas", "dele", "deles",
			"depois", "dessa", "dessas", "desse", "desses", "desta", "destas", "deste", "deste", "destes", "deve",
			"devem", "devendo", "dever", "deverá", "deverão", "deveria", "deveriam", "devia", "deviam", "disse",
			"disso", "disto", "dito", "diz", "dizem", "do", "dos", "e", "é", "ela", "elas", "ele", "eles", "em",
			"enquanto", "entre", "era", "essa", "essas", "esse", "esses", "esta", "está", "estamos", "estão",
			"estas", "estava", "estavam", "estávamos", "este", "estes", "estou", "eu", "fazendo", "fazer", "feita",
			"feitas", "feito", "feitos", "foi", "for", "foram", "fosse", "fossem", "grande", "grandes", "há", "isso",
			"isto", "já", "la", "lá", "lhe", "lhes", "lo", "mas", "me", "mesma", "mesmas", "mesmo", "mesmos", "meu",
			"meus", "minha", "minhas", "muita", "muitas", "muito", "muitos", "na", "não", "nas", "nem", "nenhum",
			"nessa", "nessas", "nesta", "nestas", "ninguém", "no", "nos", "nós", "nossa", "nossas", "nosso", "nossos",
			"num", "numa", "nunca", "o", "os", "ou", "outra", "outras", "outro", "outros", "para", "pela", "pelas",
			"pelo", "pelos", "pequena", "pequenas", "pequeno", "pequenos", "per", "perante", "pode", "pude", "podendo",
			"poder", "poderia", "poderiam", "podia", "podiam", "pois", "por", "porém", "porque", "posso", "pouca",
			"poucas", "pouco", "poucos", "primeiro", "primeiros", "própria", "próprias", "próprio", "próprios", "quais",
			"qual", "quando", "quanto", "quantos", "que", "quem", "são", "se", "seja", "sejam", "sem", "sempre",
			"sendo", "será", "serão", "seu", "seus", "si", "sido", "só", "sob", "sobre", "sua", "suas", "talvez", "também",
			"tampouco", "te", "tem", "tendo", "tenha", "ter", "teu", "teus", "ti", "tido", "tinha", "tinham", "toda",
			"todas", "todavia", "todo", "todos", "tu", "tua", "tuas", "tudo", "última", "últimas", "último", "últimos",
			"um", "uma", "umas", "uns", "vendo", "ver", "vez", "vindo", "vir", "vos", "vós", "https", "http" };

	public static final String[] STOPWORDS_ENGLISH = { "a's", "able", "about", "above", "according", "accordingly",
			"across", "actually", "after", "afterwards", "again", "against", "ain't", "all", "allow", "allows",
			"almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an",
			"and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere",
			"apart", "appear", "appreciate", "appropriate", "are", "aren't", "around", "as", "aside", "ask", "asking",
			"associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes",
			"becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides",
			"best", "better", "between", "beyond", "both", "brief", "but", "by", "c'mon", "c's", "came", "can",
			"can't", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com",
			"come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains",
			"corresponding", "could", "couldn't", "course", "currently", "definitely", "described", "despite", "did",
			"didn't", "different", "do", "does", "doesn't", "doing", "don't", "done", "down", "downwards", "during",
			"each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et",
			"etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example",
			"except", "far", "few", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly",
			"forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes",
			"going", "gone", "got", "gotten", "greetings", "had", "hadn't", "happens", "hardly", "has", "hasn't", "have",
			"haven't", "having", "he", "he's", "hello", "help", "hence", "her", "here", "here's", "hereafter", "hereby",
			"herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit",
			"however", "i'd", "i'll", "i'm", "i've", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed",
			"indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isn't", "it",
			"it'd", "it'll", "it's", "its", "itself", "just", "keep", "keeps", "kept", "know", "known", "knows", "last",
			"lately", "later", "latter", "latterly", "least", "less", "lest", "let", "let's", "like", "liked", "likely",
			"little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile",
			"merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd",
			"near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no",
			"nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously",
			"of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other",
			"others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own",
			"particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably",
			"probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding",
			"regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says",
			"second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible",
			"sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldn't", "since", "six", "so",
			"some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon",
			"sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "t's", "take", "taken",
			"tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "that's", "thats", "the", "their", "theirs",
			"them", "themselves", "then", "thence", "there", "there's", "thereafter", "thereby", "therefore", "therein",
			"theres", "thereupon", "these", "they", "they'd", "they'll", "they're", "they've", "think", "third", "this",
			"thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together",
			"too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under",
			"unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses",
			"using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasn't", "way",
			"we", "we'd", "we'll", "we're", "we've", "welcome", "well", "went", "were", "weren't", "what", "what's",
			"whatever", "when", "whence", "whenever", "where", "where's", "whereafter", "whereas", "whereby", "wherein",
			"whereupon", "wherever", "whether", "which", "while", "whither", "who", "who's", "whoever", "whole", "whom",
			"whose", "why", "will", "willing", "wish", "with", "within", "without", "won't", "wonder", "would", "wouldn't",
			"yes", "yet", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves", "zero" };

	public static final String[] BADWORD_PORTUGUES = { "anus", "-nus", "baba-ovo", "babaovo", "babaca", "bacura", "bagos",
			"baitola", "bebum", "besta", "bicha", "bisca", "bixa", "boazuda", "boceta", "boco", "boc+", "boiola", "bolagato",
			"boquete", "bolcat", "bosseta", "bosta", "bostana", "brecha", "brexa", "brioco", "bronha", "buca", "buceta",
			"bunda", "bunduda", "burra", "burro", "busseta", "cachorra", "cachorro", "cadela", "caga", "cagado", "cagao",
			"cagona", "canalha", "caralho", "casseta", "cassete", "checheca", "chereca", "chibumba", "chibumbo", "chifruda",
			"chifrudo", "chota", "chochota", "chupada", "chupado", "clitoris", "clit+ris", "cocaina", "coca-na", "coco",
			"coc+", "corna", "corno", "cornuda", "cornudo", "corrupta", "corrupto", "cretina", "cretino", "cruz-credo",
			"cu", "c+", "culhao", "culh+o", "culh+es", "curalho", "cuzao", "cuz+o", "cuzuda", "cuzudo", "debil",
			"debiloide", "defunto", "demonio", "dem+nio", "difunto", "doida", "doido", "egua", "+gua", "escrota",
			"escroto", "esporrada", "esporrado", "esporro", "esp+rro", "estupida", "est+pida", "estupidez", "estupido",
			"est+pido", "fedida", "fedido", "fedor", "fedorenta", "feia", "feio", "feiosa", "feioso", "feioza", "feiozo",
			"felacao", "fela¦+o", "fenda", "foda", "fodao", "fod+o", "fode", "fodida", "fodido", "fornica", "fudendo",
			"fudecao", "fude¦+o", "fudida", "fudido", "furada", "furado", "furao", "fur+o", "furnica", "furnicar", "furo",
			"furona", "gaiata", "gaiato", "gay", "gonorrea", "gonorreia", "gosma", "gosmenta", "gosmento", "grelinho",
			"grelo", "homo-sexual", "homosexual", "homossexual", "idiota", "idiotice", "imbecil", "iscrota", "iscroto",
			"japa", "ladra", "ladrao", "ladr+o", "ladroeira", "ladrona", "lalau", "leprosa", "leproso", "lesbica", "l+sbica",
			"macaca", "macaco", "machona", "machorra", "manguaca", "mangua¦a", "masturba", "meleca", "merda", "mija", "mijada",
			"mijado", "mijo", "mocrea", "mocr+a", "mocreia", "mocr+ia", "moleca", "moleque", "mondronga", "mondrongo", "naba",
			"nadega", "nojeira", "nojenta", "nojento", "nojo", "olhota", "otaria", "ot-ria", "otario", "ot-rio", "paca",
			"paspalha", "paspalhao", "paspalho", "pau ", "peia", "peido", "pemba", "penis", "p-nis", "pentelha", "pentelho",
			"perereca", "peru", "per+", "pica", "picao", "pic+o", "pilantra", "piranha", "piroca", "piroco", "piru", "porra",
			"prega", "prostibulo", "prost-bulo", "prostituta", "prostituto", "punheta", "punhetao", "punhet+o", "pus",
			"pustula", "p+stula", "puta", "puto", "puxa-saco", "puxasaco", "rabao", "rab+o", "rabo", "rabuda", "rabudao",
			"rabud+o", "rabudo", "rabudona", "racha", "rachada", "rachadao", "rachad+o", "rachadinha", "rachadinho", "rachado",
			"ramela", "remela", "retardada", "retardado", "ridicula", "rid-cula", "rola", "rolinha", "rosca", "sacana",
			"safada", "safado", "sapatao", "sapat+o", "sifilis", "s-filis", "siririca", "tarada", "tarado", "testuda", "tezao",
			"tez+o", "tezuda", "tezudo", "trocha", "trolha", "troucha", "trouxa", "troxa", "vaca", "vagabunda", "vagabundo",
			"vagina", "veada", "veadao", "vead+o", "veado", "viada", "viado", "viadao", "viad+o", "xavasca", "xerereca",
			"xexeca", "xibiu", "xibumba", "xota", "xochota", "xoxota", "xana", "xaninha" };

	public static final String[] BADWORD_ENGLISH = { "alcoholic", "amateur", "analphabet", "anarchist", "ape", "arse",
			"arselicker", "ass", "ass master", "ass-kisser", "ass-nugget", "ass-wipe", "asshole", "baby", "backwoodsman",
			"balls", "bandit", "barbar", "bastard", "bastard", "beavis", "beginner", "biest", "bitch", "blubber gut",
			"bogeyman", "booby", "boozer", "bozo", "brain-fart", "brainless", "brainy", "brontosaurus", "brownie", "bugger",
			"bugger, silly", "bulloks", "bum", "bum-fucker", "butt", "buttfucker", "butthead", "callboy", "callgirl",
			"camel", "cannibal", "cave man", "chaavanist", "chaot", "chauvi", "cheater", "chicken", "children fucker",
			"clit", "clown", "cock", "cock master", "cock up", "cockboy", "cockfucker", "cockroach", "coky", "con merchant",
			"con-man", "country bumpkin", "cow", "creep", "creep", "cretin", "criminal", "cunt", "cunt sucker", "daywalker",
			"deathlord", "derr brain", "desperado", "devil", "dickhead", "dinosaur", "disguesting packet", "diz brain",
			"do-do", "dog", "dog, dirty", "dogshit", "donkey", "drakula", "dreamer", "drinker", "drunkard", "dufus",
			"dulles", "dumbo", "dummy", "dumpy", "egoist", "eunuch", "exhibitionist", "fake", "fanny", "farmer", "fart",
			"fart, shitty", "fatso", "fellow", "fibber", "fish", "fixer", "flake", "flash harry", "freak", "frog", "fuck",
			"fuck face", "fuck head", "fuck noggin", "fucker", "gangster", "ghost", "goose", "gorilla", "grouch", "grumpy",
			"head, fat", "hell dog", "hillbilly", "hippie", "homo", "homosexual", "hooligan", "horse fucker", "idiot",
			"ignoramus", "jack-ass", "jerk", "joker", "junkey", "killer", "lard face", "latchkey child", "learner", "liar",
			"looser", "lucky", "lumpy", "luzifer", "macho", "macker", "man, old", "minx", "missing link", "monkey", "monster",
			"motherfucker", "mucky pub", "mutant", "neanderthal", "nerfhearder", "nobody", "nurd", "nuts, numb", "oddball",
			"oger", "oil dick", "old fart", "orang-uthan", "original", "outlaw", "pack", "pain in the ass", "pavian",
			"pencil dick", "pervert", "pig", "piggy-wiggy", "pirate", "pornofreak", "prick", "prolet", "queer", "querulant",
			"rat", "rat-fink", "reject", "retard", "riff-raff", "ripper", "roboter", "rowdy", "rufian", "sack", "sadist",
			"saprophyt", "satan", "scarab", "schfincter", "shark", "shit eater", "shithead", "simulant", "skunk", "skuz bag",
			"slave", "sleeze", "sleeze bag", "slimer", "slimy bastard", "small pricked", "snail", "snake", "snob", "snot",
			"son of a bitch ", "square", "stinker", "stripper", "stunk", "swindler", "swine", "teletubby", "thief",
			"toilett cleaner", "tussi", "typ", "unlike", "vampir", "vandale", "varmit", "wallflower", "wanker",
			"wanker, bloody", "weeze bag", "whore", "wierdo", "wino", "witch", "womanizer", "woody allen", "worm", "xena",
			"xenophebe", "xenophobe", "xxx watcher", "xxx", "yak", "yeti", "zit face", "shit", "fuck", "damn", "bitch", "crap",
			"piss", "dick", "darn", "cock", "pussy", "asshole", "fag", "bastard", "slut", "douche", "bloody", "cunt",
			"bugger", "bollock", "arsehol", "sex", "erotic", "nude", "mature", "sexy", "porn", "sex", "mommybb", "busty",
			"boobs", "ass", "hardcore", "fucking" };

	public static String normalizePattern(final String str) {
		String text = str;
		text = Normalizer.normalize(text, Normalizer.Form.NFD);
		text = text.replaceAll("[^\\p{ASCII}]", "");
		text = text.replaceAll("[-+=*&;%$#@~!_°•|/:.()'\',?'/']", " ");
		text = text.replaceAll("\"", "");
		text = text.replaceAll("'", "");
		text = text.replaceAll("\r", "");
		text = text.replaceAll("\t", "");
		text = text.replaceAll("\n", "");
		text = text.replaceAll("\\s+", " ");
		return text;
	}

	public static String retiraStopwords(final String menssage, final String[] stopwords) {

		String msg = normalizePattern(menssage);

		String[] SentSplit = msg.split(" ");
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.addAll(Arrays.asList(SentSplit));
		listOfWords.removeAll(Arrays.asList(stopwords));

		StringBuilder resp = new StringBuilder();
		for (String string : listOfWords) {
			resp.append(string.toLowerCase()).append(" ");
		}

		return resp.toString();
	}

	public static String tratarTag(final String hashtag) {
		String tag = hashtag.replaceAll("#", "");
		return normalizePattern(tag);
	}

	public static String validaStopwords(final String word) {

		String msg = normalizePattern(word);
		boolean valida = true;
		for (String string : STOPWORDS_PORTUGUES) {
			if (msg.equals(string)) {
				valida = false;
			}
		}

		for (String string : STOPWORDS_ENGLISH) {
			if (msg.equals(string)) {
				valida = false;
			}
		}

		for (String string : STOPWORDS_GERAIS) {
			if (msg.equals(string)) {
				valida = false;
			}
		}

		if (valida) {
			return msg;
		} else {
			return null;
		}
	}

	public static int amountWords(final String msg) {
		String[] sentSplit = msg.split(" ");
		int numWords = sentSplit.length;
		return numWords;
	}

	public static int quantidadeBadword(final String msg, final String[] badwords) {

		String[] sentSplit = msg.split(" ");
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.addAll(Arrays.asList(sentSplit));

		int countBadword = 0;
		for (String bad : badwords) {
			if (listOfWords.contains(bad)) {
				countBadword++;
			}
		}
		return countBadword;
	}

}
