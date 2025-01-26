- Projet - TreeManager | Alban Sellier, Rémi Géraud, Noé Morizur
  - 
- Introduction

le but du projet est d'implémenté une structure complète pour faire la gestion des arbres d'une municipalité. 
Pour ce faire il faut faire 3 applications, une pour la municipalité, une pour l'association et une pour les membres.

Vous trouverez donc dans ce projet 3 classes permettant de lancer ces applications, AssociationApp, MemberApp, et MunicipalityApp. 

- Sauvegarde

Pour la gestion des sauvegardes, il y a 2 boutons en haut de l'application qui permette de sauvegarder sur la base les modifications apportées (bouton de gauche) et un autre permettant de récupérer les informations de la base. 
Ainsi, il est possible d'allumer les 3 applications en même temps et de les faire communiquer entre elles via se fichier json.

- Fonctionnement des connexions membres

Pour pouvoir se connecter, il faut au préalable avoir créé un membre dans l'association. Par un parcours de membre, il suffit ensuite de se connecter. Il n'y a pas besoin d'avoir de mot de passe pour se connecter à l'application pour simplifier le projet.