package mooc.dto;

public class QuestionQuizDTO {

	private String question;
	private String answer1;
	private String correct1;
	private String answer2;
	private String correct2;
	private String answer3;
	private String correct3;
	private int userAnswer;
	private int correctAnswer;
	private String correction;
	private String couleur;

	public void corriger() {
		if (userAnswer == correctAnswer)
			setCouleur("green");
		else
			setCouleur("red");
		if (userAnswer == 1)
			correction = correct1;
		else if (userAnswer == 2)
			correction = correct2;
		else if (userAnswer == 3)
			correction = correct3;
		else
			correction = "Il faut répondre!";

	}


	public String getCorrect1() {
		return correct1;
	}

	public void setCorrect1(String correct1) {
		this.correct1 = correct1;
	}

	public String getCorrect2() {
		return correct2;
	}

	public void setCorrect2(String correct2) {
		this.correct2 = correct2;
	}

	public String getCorrect3() {
		return correct3;
	}

	public void setCorrect3(String correct3) {
		this.correct3 = correct3;
	}

	public String getCorrection() {
		return correction;
	}

	public void setCorrection(String correction) {
		this.correction = correction;
	}

	public int getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(int userAnswer) {
		this.userAnswer = userAnswer;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public QuestionQuizDTO() {

	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}


	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

}
