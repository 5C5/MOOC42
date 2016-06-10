package mooc.dto;

public class ApprenantNotionDto {

	/** Id */
	private int idApprenant;

	/** Nom apprenant */
	private String nomApprenant;
	/** Prenom apprenant */
	private String prenomApprenant;

	/** Niveau */
	private int niveauOU;
	private int niveauET;
	private int niveauNOT;
	private int niveauXOR;
	private int niveauNAND;
	private int niveauNOR;
	private int niveauXNOR;

	public int getIdApprenant() {
		return this.idApprenant;
	}

	public void setIdApprenant(final int idApprenant) {
		this.idApprenant = idApprenant;
	}

	public String getNomApprenant() {
		return this.nomApprenant;
	}

	public void setNomApprenant(final String nomApprenant) {
		this.nomApprenant = nomApprenant;
	}

	public String getPrenomApprenant() {
		return this.prenomApprenant;
	}

	public void setPrenomApprenant(final String prenomApprenant) {
		this.prenomApprenant = prenomApprenant;
	}

	public int getNiveauOU() {
		return this.niveauOU;
	}

	public void setNiveauOU(final int niveauOU) {
		this.niveauOU = niveauOU;
	}

	public int getNiveauET() {
		return this.niveauET;
	}

	public void setNiveauET(final int niveauET) {
		this.niveauET = niveauET;
	}

	public int getNiveauNOT() {
		return this.niveauNOT;
	}

	public void setNiveauNOT(final int niveauNOT) {
		this.niveauNOT = niveauNOT;
	}

	public int getNiveauXOR() {
		return this.niveauXOR;
	}

	public void setNiveauXOR(final int niveauXOR) {
		this.niveauXOR = niveauXOR;
	}

	public int getNiveauNAND() {
		return this.niveauNAND;
	}

	public void setNiveauNAND(final int niveauNAND) {
		this.niveauNAND = niveauNAND;
	}

	public int getNiveauNOR() {
		return this.niveauNOR;
	}

	public void setNiveauNOR(final int niveauNOR) {
		this.niveauNOR = niveauNOR;
	}

	public int getNiveauXNOR() {
		return this.niveauXNOR;
	}

	public void setNiveauXNOR(final int niveauXNOR) {
		this.niveauXNOR = niveauXNOR;
	}

}
