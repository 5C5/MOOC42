package dao;

import dto.Apprenant;


/**
 * The Interface ApprenantDAO.
 */
public interface ApprenantDAO {

    public Apprenant getByNom(final String nom, final String motDePasse);

}
