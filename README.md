# Projet - TreeManager
  
*Alban Sellier, Rémi Géraud, Noé Morizur*
***
### Introduction
Le but de ce projet est d'implémenter une structure complète pour la gestion des arbres d'une municipalité. Pour ce faire, trois applications sont nécessaires : une pour la municipalité, une pour l'association, et une pour les membres.

Vous trouverez donc dans ce projet trois classes permettant de lancer ces applications : AssociationApp, MemberApp, et MunicipalityApp.

### Sauvegarde
Pour la gestion des sauvegardes, deux boutons sont disponibles en haut de l'application. Le premier permet de sauvegarder les modifications apportées dans la base de données (bouton de gauche), tandis que le second permet de récupérer les informations depuis la base.

Ainsi, il est possible d'exécuter les trois applications simultanément et de les faire communiquer entre elles via un fichier JSON.

### Fonctionnement des connexions membres
Pour se connecter, il faut d'abord avoir créé un membre dans l'association. Après avoir parcouru les membres existants, il suffit ensuite de se connecter. Il n'est pas nécessaire d'utiliser un mot de passe pour se connecter à l'application, afin de simplifier le projet.