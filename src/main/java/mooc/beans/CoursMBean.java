package mooc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import lombok.NoArgsConstructor;
import mooc.dto.QuestionQuizDTO;
import mooc.login.AbstractMBean;
import mooc.moteur.Exercice;
import mooc.service.NotionService;
import mooc.utils.Constants;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.springframework.context.annotation.Scope;

/**
 * Bean de gestion des cours
 */
@NoArgsConstructor
@ManagedBean
@Scope("view")
public class CoursMBean extends AbstractMBean implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7390696210036765913L;

	/** Service Notion */
	@ManagedProperty(value = "#{notionService}")
	private NotionService notionService;

	/** Langue de l'utilisateur */
	private String langue;

	/**
	 * Quiz, stockage des réponses
	 */
	private List<QuestionQuizDTO> questions;

	/**
	 * Affichage des conseils
	 */
	private boolean afficher;

	/**
	 * Exercices
	 */
	/** Numero de l'exercice */
	private Integer numExercice = 0;
	/** Nombre d'exercice */
	private Integer nbExercice = 0;
	/** Nombre d'exercice restant */
	private Integer nbExerciceRestant = 0;
	/** Exercice */
	private Exercice exercice;

	@PostConstruct
	public void init() {
		this.langue = this.getLangue();
		this.initQuestions();
		System.out.println("inint");

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.COURS);
		this.numExercice = (Integer) request.getSession().getAttribute(Constants.COURS_NUM_EXERCICE);
		this.nbExercice = (Integer) request.getSession().getAttribute(Constants.COURS_NB_EXERCICE);
		this.nbExerciceRestant = (Integer) request.getSession().getAttribute(Constants.COURS_NB_EXERCICE_RESTANT);

		if (this.exercice != null) {
			for (Element el : this.exercice.getRoot().getElements()) {
				System.out.println(el.getStyleClass() + " " + el.getData());
			}
		}
	}

	public void initQuestions() {

		this.afficher = false;
		this.questions = new ArrayList<QuestionQuizDTO>();

		QuestionQuizDTO q1 = new QuestionQuizDTO();
		q1.setQuestion("Question 1 : Que sont les portes logiques ?");
		q1.setAnswer1("Des réalisations physiques de fonctions booléennes");
		q1.setAnswer2("Des fonctions booléennes");
		q1.setAnswer3("Des portes qui posent des énigmes");
		q1.setCorrect1("Bravo! C'est la bonne réponse!");
		q1.setCorrect2("Pas exactement");
		q1.setCorrect3("Euh.... vraiment?");
		q1.setCorrectAnswer(1);

		this.questions.add(q1);

		QuestionQuizDTO q2 = new QuestionQuizDTO();
		q2.setQuestion("Question 2 : Sur quel système numérique fonctionnent les portes logiques ?");
		q2.setAnswer1("le système binaire");
		q2.setAnswer2("le système décimal");
		q2.setAnswer3("Le système digestif");
		q2.setCorrectAnswer(1);
		q2.setCorrect1("Bravo! C'est la bonne réponse!");
		q2.setCorrect2("Pas exactement");
		q2.setCorrect3("Euh.... vraiment?");

		this.questions.add(q2);

		QuestionQuizDTO q3 = new QuestionQuizDTO();
		q3.setQuestion(
				"Question 3 : Dans un circuit électronique, quelle valeur numérique représente une tension nulle ?");
		q3.setAnswer1("2");
		q3.setAnswer2("1");
		q3.setAnswer3("0");
		q3.setCorrectAnswer(3);
		q3.setCorrect3("Bravo! C'est la bonne réponse!");
		q3.setCorrect2("Attention! Relis bien");
		q3.setCorrect1("Attention! Relis bien");

		this.questions.add(q3);

		QuestionQuizDTO q4 = new QuestionQuizDTO();
		q4.setQuestion(
				"Question 4 : Dans un circuit électronique, quelle valeur numérique représente une tension non-nulle ?");
		q4.setAnswer1("2");
		q4.setAnswer2("1");
		q4.setAnswer3("0");
		q4.setCorrectAnswer(2);
		q4.setCorrect2("Bravo! C'est la bonne réponse!");
		q4.setCorrect1("Attention! Relis bien");
		q4.setCorrect3("Attention! Relis bien");

		this.questions.add(q4);

		QuestionQuizDTO q5 = new QuestionQuizDTO();
		q5.setQuestion("Question 5 : Pourquoi utilise-t-on le système binaire dans les circuits électroniques ?");
		q5.setAnswer1("Parce que c’est plus simple");
		q5.setAnswer2("Parce que les circuits ne peuvent manipuler que des 0 et des 1");
		q5.setAnswer3("Parce que les informaticiens aiment se compliquer la vie");
		q5.setCorrectAnswer(2);
		q5.setCorrect2("Bravo! C'est la bonne réponse!");
		q5.setCorrect1("Pas exactement");
		q5.setCorrect3("Euh.... vraiment?");
		this.questions.add(q5);

		QuestionQuizDTO q6 = new QuestionQuizDTO();
		q6.setQuestion("Question 6 : Qu’est ce que l’algèbre de Boole ?");
		q6.setAnswer1("Une branche des mathématiques qui permet d’effectuer des calculs binaire");
		q6.setAnswer2("Une branche des mathématiques qui permet d’effectuer des calculs décimaux");
		q6.setAnswer3("Une branche des mathématiques qui permet d’étudier le jeu de quille");
		q6.setCorrectAnswer(1);
		q6.setCorrect1("Bravo! C'est la bonne réponse!");
		q6.setCorrect2("Pas exactement");
		q6.setCorrect3("Euh.... vraiment?");

		this.questions.add(q6);

		QuestionQuizDTO q7 = new QuestionQuizDTO();
		q7.setQuestion("Question 7 : A quel conjonction de coordination est associée l’opération de disjonction ?");
		q7.setAnswer1("ou");
		q7.setAnswer2("et");
		q7.setAnswer3("ni");
		q7.setCorrectAnswer(1);
		q7.setCorrect1("Bravo! C'est la bonne réponse!");
		q7.setCorrect2("Attention! Relis bien");
		q7.setCorrect3("Attention! Relis bien");

		this.questions.add(q7);

		QuestionQuizDTO q8 = new QuestionQuizDTO();
		q8.setQuestion("Question 8 : A quel conjonction de coordination est associée l’opération de conjonction ?");
		q8.setAnswer1("ou");
		q8.setAnswer2("et");
		q8.setAnswer3("car");
		q8.setCorrectAnswer(2);
		q8.setCorrect2("Bravo! C'est la bonne réponse!");
		q8.setCorrect1("Attention! Relis bien");
		q8.setCorrect3("Attention! Relis bien");

		this.questions.add(q8);

		QuestionQuizDTO q9 = new QuestionQuizDTO();
		q9.setQuestion("Question 9 : A quel expression est associée l’opération de négation ?");
		q9.setAnswer1("peut-être");
		q9.setAnswer2("oui");
		q9.setAnswer3("non");
		q9.setCorrectAnswer(3);
		q9.setCorrect3("Bravo! C'est la bonne réponse!");
		q9.setCorrect2("Attention! Relis bien");
		q9.setCorrect1("Attention! Relis bien");

		this.questions.add(q9);

		QuestionQuizDTO q10 = new QuestionQuizDTO();
		q10.setQuestion(
				"Question 10 : Comment les portes logiques permettent elles de réaliser les opérations booléenne?");
		q10.setAnswer1("En consultant leur table de vérité");
		q10.setAnswer2("En utilisant leurs calculatrices");
		q10.setAnswer3("En faisant passer le courant ou non selon les entrées");
		q10.setCorrectAnswer(3);
		q10.setCorrect3("Bravo! C'est la bonne réponse!");
		q10.setCorrect1("Pas exactement");
		q10.setCorrect2("Euh.... vraiment?");

		this.questions.add(q10);


	}

	public void corriger() {
		this.afficher = true;
		Iterator<QuestionQuizDTO> it = this.questions.iterator();
		while (it.hasNext()) {
			it.next().corriger();
		}
	}

	public List<QuestionQuizDTO> getQuestions() {
		return this.questions;
	}

	public boolean isAfficher() {
		return this.afficher;
	}

	@Override
	public String getLangue() {
		return this.langue;
	}

	public void setLangue(final String langue) {
		this.langue = langue;
	}

	public Integer getNumExercice() {
		return this.numExercice;
	}

	public void setNumExercice(final Integer numExercice) {
		this.numExercice = numExercice;
	}

	public Integer getNbExercice() {
		return this.nbExercice;
	}

	public void setNbExercice(final Integer nbExercice) {
		this.nbExercice = nbExercice;
	}

	public Integer getNbExerciceRestant() {
		return this.nbExerciceRestant;
	}

	public void setNbExerciceRestant(final Integer nbExerciceRestant) {
		this.nbExerciceRestant = nbExerciceRestant;
	}

	public void setQuestions(final List<QuestionQuizDTO> questions) {
		this.questions = questions;
	}

	public void setAfficher(final boolean afficher) {
		this.afficher = afficher;
	}

	public Exercice getExercice() {
		return this.exercice;
	}

	public void setExercice(final Exercice exercice) {
		this.exercice = exercice;
	}


	public void genererExo1() {
		this.numExercice = 1;
		this.nbExercice = 3;
		this.nbExerciceRestant = 0;
		this.exercice = new Exercice();
		this.exercice.setNotions(this.notionService.getPortesFondamentales());
		this.exercice.setDifficulte(1);
		// Porte fixe, il faut trouver les entrees
		this.exercice.setType(1);
		this.exercice.generer();
		System.out.println(this.exercice);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
		request.getSession().setAttribute(Constants.COURS_NUM_EXERCICE, this.numExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE, this.nbExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_RESTANT, this.nbExerciceRestant);
	}

	public void solutionRecalculer(final String idElement) {
		// Recuperation de l'exercice
		String form = "form_exer:panelGeneral:diag1-";
		System.out.println(idElement);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.COURS);
		System.out.println(this.exercice);
		DefaultDiagramModel root = this.exercice.getRoot();

		// Mise a jour des valeurs lors d'un clic dessus
		for (Element el : root.getElements()) {
			if ((form + el.getId()).equals(idElement)) {
				if(!Constants.SORTIE_SOLUTION.equalsIgnoreCase(el.getStyleClass()) && !Constants.SORTIE_UTILISATEUR.equalsIgnoreCase(el.getStyleClass())){
					el.setData(this.exercice.switchData(el));
					System.out.println("Change " + el.getData());
				}
			}
		}

		// Modification de l'exercice
		this.exercice.setRoot(root);
		if (this.exercice != null) {
			for (Element el : this.exercice.getRoot().getElements()) {
				System.out.println(el.getStyleClass() + " " + el.getData());
			}
		}
		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
	}

	private int genererNombreAlea(final int max) {
		int alea = (int) (Math.random() * max);
		return alea;
	}

	public NotionService getNotionService() {
		return this.notionService;
	}

	public void setNotionService(final NotionService notionService) {
		this.notionService = notionService;
	}
}
