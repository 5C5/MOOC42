package mooc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;

import lombok.Data;
import lombok.NoArgsConstructor;
import mooc.dto.QuestionQuizDTO;
import mooc.login.AbstractMBean;

/**
 * Bean de gestion des cours
 */
@Data
@NoArgsConstructor
@ManagedBean
@Scope("view")
public class CoursMBean extends AbstractMBean implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7390696210036765913L;

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

	@PostConstruct
	public void init() {
		this.langue = this.getLangue();
		this.initQuestions();
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

		questions.add(q1);

		QuestionQuizDTO q2 = new QuestionQuizDTO();
		q2.setQuestion("Question 2 : Sur quel système numérique fonctionnent les portes logiques ?");
		q2.setAnswer1("Le système binaire");
		q2.setAnswer2("Le système décimal");
		q2.setAnswer3("Le système digestif");
		q2.setCorrectAnswer(1);
		q2.setCorrect1("Bravo! C'est la bonne réponse!");
		q2.setCorrect2("Pas exactement");
		q2.setCorrect3("Euh.... vraiment?");

		questions.add(q2);

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

		questions.add(q3);

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

		questions.add(q4);

		QuestionQuizDTO q5 = new QuestionQuizDTO();
		q5.setQuestion("Question 5 : Pourquoi utilise-t-on le système binaire dans les circuits électroniques ?");
		q5.setAnswer1("Parce que c’est plus simple");
		q5.setAnswer2("Parce que les circuits ne peuvent manipuler que des 0 et des 1");
		q5.setAnswer3("Parce que les informaticiens aiment se compliquer la vie");
		q5.setCorrectAnswer(2);
		q5.setCorrect2("Bravo! C'est la bonne réponse!");
		q5.setCorrect1("Pas exactement");
		q5.setCorrect3("Euh.... vraiment?");
		questions.add(q5);

		QuestionQuizDTO q6 = new QuestionQuizDTO();
		q6.setQuestion("Question 6 : Qu’est ce que l’algèbre de Boole ?");
		q6.setAnswer1("Une branche des mathématiques qui permet d’effectuer des calculs binaire");
		q6.setAnswer2("Une branche des mathématiques qui permet d’effectuer des calculs décimaux");
		q6.setAnswer3("Une branche des mathématiques qui permet d’étudier le jeu de quille");
		q6.setCorrectAnswer(1);
		q6.setCorrect1("Bravo! C'est la bonne réponse!");
		q6.setCorrect2("Pas exactement");
		q6.setCorrect3("Euh.... vraiment?");

		questions.add(q6);

		QuestionQuizDTO q7 = new QuestionQuizDTO();
		q7.setQuestion("Question 7 : A quel conjonction de coordination est associée l’opération de disjonction ?");
		q7.setAnswer1("Ou");
		q7.setAnswer2("Et");
		q7.setAnswer3("Ni");
		q7.setCorrectAnswer(1);
		q7.setCorrect1("Bravo! C'est la bonne réponse!");
		q7.setCorrect2("Attention! Relis bien");
		q7.setCorrect3("Attention! Relis bien");

		questions.add(q7);

		QuestionQuizDTO q8 = new QuestionQuizDTO();
		q8.setQuestion("Question 8 : A quel conjonction de coordination est associée l’opération de conjonction ?");
		q8.setAnswer1("Ou");
		q8.setAnswer2("Et");
		q8.setAnswer3("Car");
		q8.setCorrectAnswer(2);
		q8.setCorrect2("Bravo! C'est la bonne réponse!");
		q8.setCorrect1("Attention! Relis bien");
		q8.setCorrect3("Attention! Relis bien");

		questions.add(q8);

		QuestionQuizDTO q9 = new QuestionQuizDTO();
		q9.setQuestion("Question 9 : A quel expression est associée l’opération de négation ?");
		q9.setAnswer1("Peut-être");
		q9.setAnswer2("Oui");
		q9.setAnswer3("Non");
		q9.setCorrectAnswer(3);
		q9.setCorrect3("Bravo! C'est la bonne réponse!");
		q9.setCorrect2("Attention! Relis bien");
		q9.setCorrect1("Attention! Relis bien");

		questions.add(q9);

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

		questions.add(q10);


	}

	public void corriger() {
		this.afficher = true;
		Iterator<QuestionQuizDTO> it = this.questions.iterator();
		while (it.hasNext())
			it.next().corriger();
	}

	public List<QuestionQuizDTO> getQuestions() {
		return questions;
	}

	public boolean isAfficher() {
		return afficher;
	}

}
