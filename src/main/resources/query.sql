-- Liste des niveaux (connaissance des notions)
select a.prenom, a.nom, n.nom_notion, con.niveau
from Apprenant a, Notion n, Connaissance con
where a.id_apprenant = con.id_apprenant
and con.id_notion = n.id_notion;