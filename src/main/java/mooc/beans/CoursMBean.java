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
import mooc.dto.NotionDto;
import mooc.dto.QuestionQuizDTO;
import mooc.login.AbstractMBean;
import mooc.moteur.Exercice;
import mooc.service.CompetenceService;
import mooc.service.ConnaissanceService;
import mooc.service.NotionService;
import mooc.utils.Constants;

import org.primefaces.context.RequestContext;
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

	/** Service Competence */
	@ManagedProperty(value = "#{competenceService}")
	private CompetenceService competenceService;

	/** Service Connaissance */
	@ManagedProperty(value = "#{connaissanceService}")
	private ConnaissanceService connaissanceService;

	/** Langue de l'utilisateur */
	private String langue;

	/**
	 * Quiz, stockage des r�ponses
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
	private Integer nbExerciceFait = 0;
	/** Exercice */
	private Exercice exercice;

	private Boolean reussi = false;

	@PostConstruct
	public void init() {
		this.langue = this.getLangue();
		this.initQuestions();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.COURS);
		this.numExercice = (Integer) request.getSession().getAttribute(Constants.COURS_NUM_EXERCICE);
		this.nbExercice = (Integer) request.getSession().getAttribute(Constants.COURS_NB_EXERCICE);
		this.nbExerciceFait = (Integer) request.getSession().getAttribute(Constants.COURS_NB_EXERCICE_FAIT);
		if (this.exercice == null) {
			this.numExercice = 0;
			this.nbExercice = 0;
			this.nbExerciceFait = 0;
			this.reussi = false;
		}
	}

	public void initQuestions() {

		this.afficher = false;
		this.questions = new ArrayList<QuestionQuizDTO>();

		QuestionQuizDTO q1 = new QuestionQuizDTO();
		q1.setQuestion("Question 1 : Que sont les portes logiques ?");
		q1.setAnswer1("Des r�alisations physiques de fonctions bool�ennes");
		q1.setAnswer2("Des fonctions bool�ennes");
		q1.setAnswer3("Des portes qui posent des �nigmes");
		q1.setCorrect1("Bravo! C'est la bonne r�ponse!");
		q1.setCorrect2("Pas exactement");
		q1.setCorrect3("Euh.... vraiment?");
		q1.setCorrectAnswer(1);

		this.questions.add(q1);

		QuestionQuizDTO q2 = new QuestionQuizDTO();
		q2.setQuestion("Question 2 : Sur quel syst�me num�rique fonctionnent les portes logiques ?");
		q2.setAnswer1("Le syst�me binaire");
		q2.setAnswer2("Le syst�me d�cimal");
		q2.setAnswer3("Le syst�me digestif");
		q2.setCorrectAnswer(1);
		q2.setCorrect1("Bravo! C'est la bonne r�ponse!");
		q2.setCorrect2("Pas exactement");
		q2.setCorrect3("Euh.... vraiment?");

		this.questions.add(q2);

		QuestionQuizDTO q3 = new QuestionQuizDTO();
		q3.setQuestion(
				"Question 3 : Dans un circuit �lectronique, quelle valeur num�rique repr�sente une tension nulle ?");
		q3.setAnswer1("2");
		q3.setAnswer2("1");
		q3.setAnswer3("0");
		q3.setCorrectAnswer(3);
		q3.setCorrect3("Bravo! C'est la bonne r�ponse!");
		q3.setCorrect2("Attention! Relis bien");
		q3.setCorrect1("Attention! Relis bien");

		this.questions.add(q3);

		QuestionQuizDTO q4 = new QuestionQuizDTO();
		q4.setQuestion(
				"Question 4 : Dans un circuit �lectronique, quelle valeur num�rique repr�sente une tension non-nulle ?");
		q4.setAnswer1("2");
		q4.setAnswer2("1");
		q4.setAnswer3("0");
		q4.setCorrectAnswer(2);
		q4.setCorrect2("Bravo! C'est la bonne r�ponse!");
		q4.setCorrect1("Attention! Relis bien");
		q4.setCorrect3("Attention! Relis bien");

		this.questions.add(q4);

		QuestionQuizDTO q5 = new QuestionQuizDTO();
		q5.setQuestion("Question 5 : Pourquoi utilise-t-on le syst�me binaire dans les circuits �lectroniques ?");
		q5.setAnswer1("Parce que c'est plus simple");
		q5.setAnswer2("Parce que les circuits ne peuvent manipuler que des 0 et des 1");
		q5.setAnswer3("Parce que les informaticiens aiment se compliquer la vie");
		q5.setCorrectAnswer(2);
		q5.setCorrect2("Bravo! C'est la bonne r�ponse!");
		q5.setCorrect1("Pas exactement");
		q5.setCorrect3("Euh.... vraiment?");
		this.questions.add(q5);

		QuestionQuizDTO q6 = new QuestionQuizDTO();
		q6.setQuestion("Question 6 : Qu'est ce que l'alg�bre de Boole ?");
		q6.setAnswer1("Une branche des math�matiques qui permet d'effectuer des calculs binaire");
		q6.setAnswer2("Une branche des math�matiques qui permet d'effectuer des calculs d�cimaux");
		q6.setAnswer3("Une branche des math�matiques qui permet d'�tudier le jeu de quille");
		q6.setCorrectAnswer(1);
		q6.setCorrect1("Bravo! C'est la bonne r�ponse!");
		q6.setCorrect2("Pas exactement");
		q6.setCorrect3("Euh.... vraiment?");

		this.questions.add(q6);

		QuestionQuizDTO q7 = new QuestionQuizDTO();
		q7.setQuestion("Question 7 : A quel conjonction de coordination est associ�e l'op�ration de disjonction ?");
		q7.setAnswer1("Ou");
		q7.setAnswer2("Et");
		q7.setAnswer3("Ni");
		q7.setCorrectAnswer(1);
		q7.setCorrect1("Bravo! C'est la bonne r�ponse!");
		q7.setCorrect2("Attention! Relis bien");
		q7.setCorrect3("Attention! Relis bien");

		this.questions.add(q7);

		QuestionQuizDTO q8 = new QuestionQuizDTO();
		q8.setQuestion("Question 8 : A quel conjonction de coordination est associ�e l'op�ration de conjonction ?");
		q8.setAnswer1("Ou");
		q8.setAnswer2("Et");
		q8.setAnswer3("Car");
		q8.setCorrectAnswer(2);
		q8.setCorrect2("Bravo! C'est la bonne r�ponse!");
		q8.setCorrect1("Attention! Relis bien");
		q8.setCorrect3("Attention! Relis bien");

		this.questions.add(q8);

		QuestionQuizDTO q9 = new QuestionQuizDTO();
		q9.setQuestion("Question 9 : A quel expression est associ�e l'op�ration de n�gation ?");
		q9.setAnswer1("Peut-�tre");
		q9.setAnswer2("Oui");
		q9.setAnswer3("Non");
		q9.setCorrectAnswer(3);
		q9.setCorrect3("Bravo! C'est la bonne r�ponse!");
		q9.setCorrect2("Attention! Relis bien");
		q9.setCorrect1("Attention! Relis bien");

		this.questions.add(q9);

		QuestionQuizDTO q10 = new QuestionQuizDTO();
		q10.setQuestion(
				"Question 10 : Comment les portes logiques permettent elles de r�aliser les op�rations bool�enne?");
		q10.setAnswer1("En consultant leur table de v�rit�");
		q10.setAnswer2("En utilisant leurs calculatrices");
		q10.setAnswer3("En faisant passer le courant ou non selon les entr�es");
		q10.setCorrectAnswer(3);
		q10.setCorrect3("Bravo! C'est la bonne r�ponse!");
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

	public Integer getNbExerciceFait() {
		return this.nbExerciceFait;
	}

	public void setNbExerciceFait(final Integer nbExerciceFait) {
		this.nbExerciceFait = nbExerciceFait;
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




	public void solutionRecalculer(final String idElement) {
		// Recuperation de l'exercice
		String form1 = "form_exer:panelGeneral:diag1-";
		String form2 = "form_exer:panelGeneral:diag2-";
		String form3 = "form_exer:panelGeneral:diag3-";
		System.out.println(idElement);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.COURS);
		System.out.println(this.exercice);
		DefaultDiagramModel root = this.exercice.getRoot();

		// Mise a jour des valeurs lors d'un clic dessus
		for (Element el : root.getElements()) {
			if ((form1 + el.getId()).equals(idElement)) {
				if(!Constants.SORTIE_SOLUTION.equalsIgnoreCase(el.getStyleClass()) && !Constants.SORTIE_UTILISATEUR.equalsIgnoreCase(el.getStyleClass())){
					el.setData(this.exercice.switchData(el));
					System.out.println("Change " + el.getData());
				}
			} else if ((form2 + el.getId()).equals(idElement)) {
				if(!Constants.SORTIE_SOLUTION.equalsIgnoreCase(el.getStyleClass()) && !Constants.SORTIE_UTILISATEUR.equalsIgnoreCase(el.getStyleClass())){
					el.setData(this.exercice.switchData(el));
					System.out.println("Change " + el.getData());
				}
			} else if ((form3 + el.getId()).equals(idElement)) {
				if(!Constants.SORTIE_SOLUTION.equalsIgnoreCase(el.getStyleClass()) && !Constants.SORTIE_UTILISATEUR.equalsIgnoreCase(el.getStyleClass())){
					el.setData(this.exercice.switchData(el));
					System.out.println("Change " + el.getData());
				}
			}
		}

		// Modification de l'exercice
		this.exercice.setRoot(root);

		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
	}

	private int genererNombreAlea(final int max) {
		int alea = (int) (Math.random() * max);
		return alea;
	}

	public void valider() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Integer id = (Integer) request.getSession().getAttribute(Constants.UTILISATEUR_CONNECTE);
		this.exercice = (Exercice) request.getSession().getAttribute(Constants.COURS);
		this.exercice.setNbEssai(this.exercice.getNbEssai() + 1);
		// Verification si la solution utilisateur est correcte
		boolean verif = this.exercice.valider(this.exercice.getRoot());
		RequestContext context = RequestContext.getCurrentInstance();

		if (verif) {
			if (id != null) {
				// Enregistrement de l'exercice pour l'apprenant
				this.competenceService.ajouterExercice(id,this.exercice.getNotions(),this.exercice.getDifficulte(),100 / this.exercice.getNbEssai());
				List<String> portes = this.exercice.getPorteUtilisee();
				this.connaissanceService.majNiveauConnaissance(id, portes,this.exercice.getDifficulte());
			}
			this.reussi = true;
			this.nbExerciceFait = this.nbExerciceFait + 1;
			request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_FAIT,this.nbExerciceFait);
			System.out.println(this.nbExerciceFait);
			if (this.numExercice == 1) {
				context.execute("PF('dialogExoReussi1').show();");
			} else if (this.numExercice == 2) {
				context.execute("PF('dialogExoReussi2').show();");
			} else if (this.numExercice == 3) {
				context.execute("PF('dialogExoReussi3').show();");
			}

		} else {
			this.reussi = false;
			if (this.numExercice == 1) {
				context.execute("PF('dialogExoRate1').show();");
			} else if (this.numExercice == 2) {
				context.execute("PF('dialogExoRate2').show();");
			} else if (this.numExercice == 3) {
				context.execute("PF('dialogExoRate3').show();");
			}
		}
	}

	public void continuer(){
		System.out.println("Continuer ");
		if (this.numExercice == 1) {
			this.continuerExo1();
		} else if (this.numExercice == 2) {
			this.continuerExo2();
		} else if (this.numExercice == 3) {
			this.continuerExo3();
		}
	}

	public void arreter(){
		System.out.println("Arreter");
		this.exercice = null;
		this.numExercice = 0;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.COURS);
	}

	public NotionService getNotionService() {
		return this.notionService;
	}

	public void setNotionService(final NotionService notionService) {
		this.notionService = notionService;
	}

	public CompetenceService getCompetenceService() {
		return this.competenceService;
	}

	public void setCompetenceService(final CompetenceService competenceService) {
		this.competenceService = competenceService;
	}

	public ConnaissanceService getConnaissanceService() {
		return this.connaissanceService;
	}

	public void setConnaissanceService(final ConnaissanceService connaissanceService) {
		this.connaissanceService = connaissanceService;
	}

	public Boolean getReussi() {
		return this.reussi;
	}

	public void setReussi(final Boolean reussi) {
		this.reussi = reussi;
	}

	public void genererExo1() {
		this.numExercice = 1;
		this.nbExercice = 3;
		this.nbExerciceFait = 1;
		this.exercice = new Exercice();
		// Porte OR
		List<NotionDto> notions = new ArrayList<NotionDto>();
		NotionDto or = this.notionService.getByLibelle(Constants.OR);
		notions.add(or);
		this.exercice.setNotions(notions);
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
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_FAIT, this.nbExerciceFait);
	}
	public void continuerExo1(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.nbExerciceFait = (Integer) request.getSession().getAttribute(Constants.COURS_NB_EXERCICE_FAIT);
		this.exercice = new Exercice();
		if(this.nbExerciceFait == 2){
			// Porte AND
			List<NotionDto> notions = new ArrayList<NotionDto>();
			NotionDto or = this.notionService.getByLibelle(Constants.AND);
			notions.add(or);
			this.exercice.setNotions(notions);
			this.exercice.setDifficulte(1);
			this.exercice.setType(1);
			this.exercice.generer();
			System.out.println(this.exercice);
		} else if (this.nbExerciceFait == 3){
			// Porte NOT
			List<NotionDto> notions = new ArrayList<NotionDto>();
			NotionDto or = this.notionService.getByLibelle(Constants.NOT);
			notions.add(or);
			this.exercice.setNotions(notions);
			this.exercice.setDifficulte(1);
			this.exercice.setType(1);
			this.exercice.generer();
			System.out.println(this.exercice);
		} else {
			// C'est fini
			this.arreter();
		}
		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
		request.getSession().setAttribute(Constants.COURS_NUM_EXERCICE, this.numExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE, this.nbExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_FAIT, this.nbExerciceFait);
	}

	public void genererExo2() {
		this.numExercice = 2;
		this.nbExercice = 4;
		this.nbExerciceFait = 1;
		this.exercice = new Exercice();
		this.exercice.setNotions(this.notionService.getPortesFondamentales());
		this.exercice.setDifficulte(2);
		// Porte fixe, il faut trouver les entrees
		this.exercice.setType(1);
		this.exercice.generer();
		System.out.println(this.exercice);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
		request.getSession().setAttribute(Constants.COURS_NUM_EXERCICE, this.numExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE, this.nbExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_FAIT, this.nbExerciceFait);
	}

	public void continuerExo2() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.nbExerciceFait = (Integer) request.getSession().getAttribute(Constants.COURS_NB_EXERCICE_FAIT);
		this.exercice = new Exercice();
		if (this.nbExerciceFait == this.nbExercice + 1) {
			// C'est fini
			this.arreter();
		} else {
			this.exercice = new Exercice();
			this.exercice.setNotions(this.notionService.getPortesFondamentales());
			this.exercice.setDifficulte(2);
			// Porte fixe, il faut trouver les entrees
			this.exercice.setType(1);
			this.exercice.generer();
		}
		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
		request.getSession().setAttribute(Constants.COURS_NUM_EXERCICE, this.numExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE, this.nbExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_FAIT, this.nbExerciceFait);
	}

	public void genererExo3() {
		this.numExercice = 3;
		this.nbExercice = 8;
		this.nbExerciceFait = 1;
		this.exercice = new Exercice();
		// XOR, NAND, NOR, XNOR
		List<NotionDto> notions = new ArrayList<NotionDto>();
		NotionDto xor = this.notionService.getByLibelle(Constants.XOR);
		notions.add(xor);
		this.exercice.setNotions(notions);
		this.exercice.setDifficulte(1);
		// Porte fixe, il faut trouver les entrees
		this.exercice.setType(1);
		this.exercice.generer();
		System.out.println(this.exercice);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
		request.getSession().setAttribute(Constants.COURS_NUM_EXERCICE,this.numExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE,this.nbExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_FAIT,this.nbExerciceFait);
	}

	public void continuerExo3() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.nbExerciceFait = (Integer) request.getSession().getAttribute(Constants.COURS_NB_EXERCICE_FAIT);
		this.exercice = new Exercice();
		if (this.nbExerciceFait == this.nbExercice + 1) {
			// C'est fini
			this.arreter();
		} else if (this.nbExerciceFait == 2) {
			// XOR, NAND, NOR, XNOR
			List<NotionDto> notions = new ArrayList<NotionDto>();
			NotionDto porte = this.notionService.getByLibelle(Constants.NAND);
			notions.add(porte);
			this.exercice.setNotions(notions);
			this.exercice.setDifficulte(1);
			this.exercice.setType(1);
			this.exercice.generer();
		} else if (this.nbExerciceFait == 3) {
			// XOR, NAND, NOR, XNOR
			List<NotionDto> notions = new ArrayList<NotionDto>();
			NotionDto porte = this.notionService.getByLibelle(Constants.NOR);
			notions.add(porte);
			this.exercice.setNotions(notions);
			this.exercice.setDifficulte(1);
			this.exercice.setType(1);
			this.exercice.generer();
		} else if (this.nbExerciceFait == 4) {
			// XOR, NAND, NOR, XNOR
			List<NotionDto> notions = new ArrayList<NotionDto>();
			NotionDto porte = this.notionService.getByLibelle(Constants.XNOR);
			notions.add(porte);
			this.exercice.setNotions(notions);
			this.exercice.setDifficulte(1);
			this.exercice.setType(1);
			this.exercice.generer();
		} else {
			this.exercice = new Exercice();
			this.exercice.setNotions(this.notionService.getPortesComplexes());
			this.exercice.setDifficulte(2);
			// Porte fixe, il faut trouver les entrees
			this.exercice.setType(1);
			this.exercice.generer();
		}
		request.getSession().removeAttribute(Constants.COURS);
		request.getSession().setAttribute(Constants.COURS, this.exercice);
		request.getSession().setAttribute(Constants.COURS_NUM_EXERCICE,this.numExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE,this.nbExercice);
		request.getSession().setAttribute(Constants.COURS_NB_EXERCICE_FAIT,this.nbExerciceFait);
	}

}
