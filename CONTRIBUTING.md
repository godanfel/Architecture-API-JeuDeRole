# Guide de contribution — Architecture-API-JeuDeRole

Ce guide explique **comment ajouter du travail au repo sans rien casser**, selon le workflow vu en classe (*Travailler en équipe avec Git*). Si tu ne sais pas quoi faire, cherche ta situation dans la [table des matières](#table-des-matières) et suis les commandes dans l'ordre.

> **Règle n°1 : on ne travaille jamais directement sur `main` ni sur `develop`.**
> Tout passe par une branche à toi, puis une Pull Request (PR) vers `develop`.

---

## Table des matières

1. [Les branches du repo](#1-les-branches-du-repo)
2. [Configuration une seule fois](#2-configuration-une-seule-fois)
3. [Le workflow normal, étape par étape](#3-le-workflow-normal-étape-par-étape)
4. [Nommer une branche](#4-nommer-une-branche)
5. [Écrire un message de commit](#5-écrire-un-message-de-commit)
6. [Ouvrir une Pull Request](#6-ouvrir-une-pull-request)
7. [Faire une revue de code](#7-faire-une-revue-de-code)
8. [Mettre sa branche à jour et gérer un conflit](#8-mettre-sa-branche-à-jour-et-gérer-un-conflit)
9. [Release : passer `develop` dans `main`](#9-release--passer-develop-dans-main)
10. [Hotfix : bug urgent sur `main`](#10-hotfix--bug-urgent-sur-main)
11. [J'ai fait une erreur — que faire ?](#11-jai-fait-une-erreur--que-faire-)
12. [Pièges de Git Bash sur Windows](#12-pièges-de-git-bash-sur-windows)
13. [Aide-mémoire](#13-aide-mémoire)

---

## 1. Les branches du repo

| Branche | Rôle | Qui écrit dessus ? |
|---|---|---|
| `main` | Version stable, livrée. Toujours compilable. | **Personne directement.** Reçoit seulement `develop` (release) ou un `hotfix/*`. |
| `develop` | Ligne d'intégration : tout le travail terminé et revu. | **Personne directement.** Reçoit les PR des branches de travail. |
| `feature/*`, `bugfix/*`, `docs/*`, `chore/*` | Une branche par tâche. | Toi. Supprimée après le merge. |
| `hotfix/*` | Correctif urgent d'un bug déjà sur `main`. | Toi. Supprimée après le merge. |

On ne crée **pas** de branche à son nom (`Carl`, `Ian`…). Une branche = une tâche, pas une personne.

---

## 2. Configuration une seule fois

À faire une fois sur ton ordinateur.

```bash
# ton identité — l'email doit être un email de ton compte GitHub
git config --global user.name "ton-nom-github"
git config --global user.email "ton-email@exemple.com"

# vérifier
git config user.name
git config user.email

# récupérer le projet (si ce n'est pas déjà fait)
git clone https://github.com/godanfel/Architecture-API-JeuDeRole.git
cd Architecture-API-JeuDeRole
git checkout develop
```

> Si ton email n'est pas lié à ton compte GitHub, tes commits apparaissent avec un avatar gris et ne comptent pas dans tes contributions.

---

## 3. Le workflow normal, étape par étape

C'est **le** scénario de tous les jours. Les autres sections sont des variantes.

### Étape 1 — Créer (ou choisir) une issue

Sur GitHub → **Issues → New issue**. Un titre clair et actionnable, une description, des critères d'acceptation si c'est une fonctionnalité. Note son numéro (ex. `#12`).

### Étape 2 — Partir d'un `develop` à jour

```bash
git checkout develop
git pull
git checkout -b feature/12-unicite-nom-heros
```

> Toujours `git pull` **avant** de créer la branche, sinon tu pars d'une vieille version.

### Étape 3 — Coder, puis commiter par petits morceaux

```bash
git status                       # voir ce qui a changé
git add chemin/vers/Fichier.java # ajouter UNIQUEMENT les fichiers voulus
git commit -m "feat(hero): vérifie l'unicité du nom à la création"
```

- Regarde **toujours** `git status` avant `git add`.
- Évite `git add .` tant que tu n'as pas vérifié qu'il n'y a pas de fichiers parasites (`out/`, `.txt` de test, etc.).
- Un commit = un changement logique. Pas un commit fourre-tout en fin de journée.
- Vérifie que ça compile (**Build → Rebuild Project** dans IntelliJ) avant de commiter.

### Étape 4 — Mettre la branche à jour avec `develop`

D'autres PR ont peut-être été mergées pendant que tu travaillais.

```bash
git fetch origin
git rebase origin/develop
```

En cas de conflit → [section 8](#8-mettre-sa-branche-à-jour-et-gérer-un-conflit).

### Étape 5 — Pousser la branche

```bash
# premier push de cette branche
git push -u origin feature/12-unicite-nom-heros

# si tu avais déjà poussé AVANT de faire le rebase
git push --force-with-lease
```

> `--force-with-lease`, **jamais** `--force` seul. Le premier refuse d'écraser le travail de quelqu'un d'autre.

### Étape 6 — Ouvrir la Pull Request

Voir [section 6](#6-ouvrir-une-pull-request). **Base : `develop`.**

### Étape 7 — Revue, corrections, merge

- Réponds aux commentaires, pousse de nouveaux commits pour corriger (pas besoin de rouvrir une PR).
- Une fois approuvée : **Squash and merge**.

### Étape 8 — Nettoyer

```bash
git checkout develop
git pull
git branch -D feature/12-unicite-nom-heros   # supprime ta copie locale
git fetch --prune                            # oublie les branches supprimées sur GitHub
```

> `-D` (majuscule) est nécessaire après un *squash* : Git ne reconnaît pas tes commits comme mergés, donc `-d` refuserait. Ne l'utilise **qu'après** avoir confirmé sur GitHub que la PR est bien mergée.

Si la branche n'a pas été supprimée automatiquement sur GitHub :
```bash
git push origin --delete feature/12-unicite-nom-heros
```

---

## 4. Nommer une branche

Format : **`type/numéro-description-courte`** (minuscules, tirets, pas d'accents ni d'espaces).

| Type | Quand | Exemple |
|---|---|---|
| `feature/` | Nouvelle fonctionnalité | `feature/12-unicite-nom-heros` |
| `bugfix/` | Corriger un bug sur `develop` | `bugfix/15-calcul-pv-max` |
| `hotfix/` | Corriger un bug urgent sur `main` | `hotfix/health-manquant` |
| `docs/` | Documentation seulement | `docs/guide-contribution` |
| `chore/` | Config, `.gitignore`, ménage | `chore/gitignore` |
| `release/` | Préparer une version | `release/0.2.0` |

Pas d'issue ? Mets juste la description : `chore/gitignore`.

---

## 5. Écrire un message de commit

Format **Conventional Commits** :

```
type(portée): description à l'impératif

corps optionnel — POURQUOI ce changement

Closes #12
```

### Les types

| Type | Usage |
|---|---|
| `feat` | Nouvelle fonctionnalité |
| `fix` | Correction de bug |
| `docs` | Documentation seulement |
| `style` | Formatage, sans impact sur la logique |
| `refactor` | Restructuration sans ajout ni correction |
| `perf` | Amélioration de performance |
| `test` | Ajout ou correction de tests |
| `chore` | Config, dépendances, `.gitignore`… |
| `feat!` / `fix!` | Changement cassant (casse l'existant) |

La **portée** est la partie du domaine touchée : `hero`, `inventaire`, `combat`, `exceptions`, `lobby`…

### Règles

- **Impératif présent** : « ajoute », pas « ajouté » ni « ajout de ».
- **Court** : 50 à 72 caractères, **sans point final**.
- Le **corps** explique le *pourquoi* ; le code montre déjà le *comment*.
- `Closes #N` en bas pour fermer l'issue automatiquement au merge.

### Exemples

```bash
git commit -m "feat(hero): ajoute la montée de niveau"
git commit -m "fix(inventaire): empêche l'ajout quand l'inventaire est plein"
git commit -m "refactor(exceptions): fait hériter HealthInvalidException de DomainException"
git commit -m "docs: ajoute le guide de contribution"
git commit -m "chore: ignore le dossier out/"

# avec un corps et un footer : un -m par paragraphe
git commit -m "feat(combat): ajoute le jet d'initiative" \
           -m "Héros : 1d20 + mod. Dextérité ; créature : 1d20 + bonus." \
           -m "Closes #21"
```

### À éviter

| ❌ | ✅ |
|---|---|
| `Add files via upload` | `feat(inventaire): ajoute la potion de soin` |
| `Création du héros` | `feat(hero): ajoute la création du héros` |
| `Nettoyage de ma branche` | `chore: retire les fichiers IntelliJ en double` |
| `fix` | `fix(hero): plafonne le soin aux PV max` |

---

## 6. Ouvrir une Pull Request

### Avant d'ouvrir

- [ ] Le code compile (Build → Rebuild Project) et les tests passent
- [ ] La branche est à jour avec `develop` (`git rebase origin/develop`)
- [ ] Les commits suivent Conventional Commits
- [ ] La PR est petite et parle d'**un seul sujet** (idéalement < 400 lignes)
- [ ] Tu as relu ton propre diff sur GitHub (auto-review)

### Sur GitHub

1. Bandeau jaune **Compare & pull request** (ou **Pull requests → New pull request**).
2. **base : `develop`** ← compare : ta branche.
   ⚠️ Vérifie toujours la base. Une PR mergée dans `main` par erreur saute toute l'intégration.
3. **Titre** = même format qu'un commit : `feat(hero): ajoute la montée de niveau`.
   Avec *Squash and merge*, ce titre **devient** le commit sur `develop`.
4. **Corps** : le modèle se remplit tout seul (fichier `.github/pull_request_template.md`). Complète chaque section :
    - **Résumé** — quoi et *pourquoi* (contexte métier, règle de l'épic).
    - **Changements** — 3 ou 4 points, pas un résumé ligne par ligne.
    - **Comment tester** — étapes concrètes que le reviewer peut suivre.
    - **Points d'attention** — ce qui peut casser, ce qui reste à faire.
    - **`Closes #N`** — pour fermer l'issue au merge.
5. **Reviewers** : au moins un coéquipier. **Assignee** : toi. **Labels** : `enhancement`, `bug`…
6. Une fois approuvée : **Squash and merge**.

---

## 7. Faire une revue de code

### En tant que reviewer

Un bon commentaire contient :
1. **Le problème précis** — la ligne, l'appel visé.
2. **Le principe enfreint** — nommé : SRP, Déméter, DRY, couplage, encapsulation…
3. **La conséquence** — pourquoi ça pose problème pour la suite.
4. **Une suggestion** — une piste concrète.

Préfixe `nit:` pour une remarque mineure non bloquante (nommage, espacement, typo).
Souligne aussi ce qui est bien fait. Approuve dès que c'est *suffisamment bon* — ne bloque pas pour la perfection.

> ❌ « change ça », « bof », « t'es sûr d'avoir testé ? »
> ✅ « `Hero` appelle `inventory.getItemList().get(0).use(...)` : on traverse deux objets, ce qui viole la loi de Déméter. Si `Inventory` change sa structure interne, `Hero` casse. Je suggère `inventory.useItem(item, this)`. »

### En tant qu'auteur

- Réponds à **tous** les commentaires (même juste « corrigé dans abc123 »).
- Ne résous pas un commentaire sans l'accord du reviewer.
- Pendant la revue, corrige avec de **nouveaux commits** (le squash les regroupera au merge).
- Ne le prends pas personnellement — c'est le code qui est revu, pas toi.

---

## 8. Mettre sa branche à jour et gérer un conflit

### Cas normal

```bash
git checkout feature/ma-branche
git fetch origin
git rebase origin/develop
git push --force-with-lease      # seulement si la branche était déjà poussée
```

**Fais-le au moins une fois par jour** si ta branche vit plusieurs jours : les conflits restent petits.

### S'il y a un conflit

Git s'arrête et affiche les fichiers en conflit. Dans le fichier :

```
<<<<<<< HEAD
version de develop
=======
ta version
>>>>>>> abc1234 (ton commit)
```

1. Ouvre le fichier (IntelliJ affiche un bouton **Resolve** avec une vue à 3 colonnes — utilise-le).
2. Garde la bonne version (ou combine les deux), **supprime les marqueurs** `<<<<<<<`, `=======`, `>>>>>>>`.
3. Vérifie que ça compile.
4. Puis :
   ```bash
   git add fichier/en/conflit.java
   git rebase --continue
   ```
5. Répète si un autre commit est en conflit.
6. À la fin : `git push --force-with-lease`.

### Je suis perdu au milieu du rebase

```bash
git rebase --abort
```
Tout revient exactement comme avant le rebase. Demande de l'aide à l'équipe.

### Règles d'or du rebase

- **Jamais** de rebase sur `main`, `develop`, ou une branche que quelqu'un d'autre utilise.
- Seulement sur **ta** branche de travail.
- Toujours `--force-with-lease`, jamais `--force`.
- Dans le doute, demande avant.

---

## 9. Release : passer `develop` dans `main`

Quand un milestone est terminé et que `develop` est stable :

1. Sur GitHub : **New pull request**, **base : `main`** ← compare : `develop`.
2. Titre : `release: v0.2.0` (ou le numéro du milestone).
3. Merger avec **Create a merge commit** — **pas** *Squash*.
   *Pourquoi ?* Un squash crée sur `main` un commit qui n'existe pas dans `develop` ; les deux branches divergent et la prochaine release aura des conflits inutiles.
4. Taguer la version sur `main` :
   ```bash
   git checkout main
   git pull
   git tag -a v0.2.0 -m "Version 0.2.0 — création de héros et inventaire"
   git push origin v0.2.0
   ```
5. (Optionnel) Sur GitHub : **Releases → Draft a new release**, choisir le tag `v0.2.0`.

**Numérotation (SemVer)** : `vMAJEUR.MINEUR.CORRECTIF`
- `feat` → MINEUR (`0.2.0` → `0.3.0`)
- `fix` → CORRECTIF (`0.2.0` → `0.2.1`)
- `feat!` / changement cassant → MAJEUR

---

## 10. Hotfix : bug urgent sur `main`

Seulement si le bug est déjà **sur `main`** (version livrée). Sinon, c'est un `bugfix/` normal depuis `develop`.

```bash
git checkout main
git pull
git checkout -b hotfix/description-du-bug
# corriger, puis
git add fichier/corrige.java
git commit -m "fix(portée): corrige ..."
git push -u origin hotfix/description-du-bug
```

1. PR **base : `main`**, merger.
2. Reporter le correctif dans `develop` (sinon il disparaîtra à la prochaine release) :
   ```bash
   git checkout develop
   git pull
   git merge origin/main -m "chore: synchronise develop avec main après hotfix"
   git push
   ```
   (Si `develop` est protégée : PR **base : `develop`** ← compare : `main`, *Create a merge commit*.)
3. Taguer `main` avec un numéro de correctif (`v0.2.1`).

---

## 11. J'ai fait une erreur — que faire ?

Avant tout : **`git status`** et **`git log --oneline -5`** pour voir où tu en es.

### J'ai modifié des fichiers mais je n'ai pas encore commité, et je veux tout annuler

```bash
git restore chemin/du/fichier.java     # annule les modifs d'un fichier
git restore .                          # annule TOUTES les modifs (irréversible)
```

### J'ai fait `git add` sur le mauvais fichier

```bash
git restore --staged chemin/du/fichier
```
Le fichier n'est plus dans le prochain commit (tes modifications restent).

### Mon dernier commit a un mauvais message (pas encore poussé)

```bash
git commit --amend -m "feat(hero): bon message"
```

### J'ai oublié un fichier dans mon dernier commit (pas encore poussé)

```bash
git add fichier/oublie.java
git commit --amend --no-edit
```

### Je veux annuler mon dernier commit mais garder les modifications (pas encore poussé)

```bash
git reset --soft HEAD~1
```

### J'ai déjà poussé et je dois corriger un commit sur **ma** branche

Fais la correction (`--amend`, `reset`…), puis :
```bash
git push --force-with-lease
```
OK **seulement** sur ta branche de travail, jamais sur `main` ou `develop`.

### J'ai commité sur `develop` (ou `main`) au lieu de ma branche — pas encore poussé

```bash
git branch feature/ma-tache           # crée la branche AVEC tes commits
git reset --hard origin/develop       # remet develop comme sur GitHub
git checkout feature/ma-tache         # continue sur ta branche
```
(Remplace `develop` par `main` si c'est sur `main`.)

### J'ai commité sur `develop` ou `main` ET poussé

**Ne réécris pas l'historique** (pas de `reset`, pas de `--force`) : les autres ont peut-être déjà récupéré ces commits.
- Si le code est correct : laisse-le et préviens l'équipe.
- S'il faut l'annuler :
  ```bash
  git revert <hash-du-commit>
  git push
  ```
  `revert` crée un nouveau commit qui défait l'ancien, sans réécrire l'historique.
- Pour que ça n'arrive plus : activer la protection de branche (voir [section 13](#paramètres-du-repo-à-faire-une-fois-par-le-propriétaire)).

### J'ai fait des modifications sur la mauvaise branche (pas encore commitées)

```bash
git stash                         # met tes modifications de côté
git checkout bonne-branche        # (ou git checkout -b nouvelle-branche)
git stash pop                     # les réapplique ici
```

### Ma PR vise la mauvaise base (ex. `main` au lieu de `develop`)

**Pas encore mergée** : sur la page de la PR, clique **Edit** à côté du titre et change la base vers `develop`.

**Déjà mergée dans `main`** : synchronise `develop` avec `main` :
```bash
git checkout develop
git pull
git merge origin/main -m "chore: synchronise develop avec main"
git push
```

### Je veux récupérer un fichier d'une autre branche

```bash
git checkout origin/autre-branche -- chemin/du/Fichier.java
```
Le fichier est copié dans ta branche actuelle, prêt à être commité.

### Je veux reprendre le travail d'un coéquipier en gardant son nom comme auteur

```bash
git checkout origin/sa-branche -- chemin/Fichier.java
git add chemin/Fichier.java
git commit --author="SonNom <son-email>" -m "feat(...): ..."
```
Pour trouver son nom et son email : `git log -1 --format='%an <%ae>' origin/sa-branche`

### J'ai supprimé une branche ou perdu des commits

```bash
git reflog
```
Liste tout ce que tu as fait récemment. Trouve le hash du commit perdu, puis :
```bash
git checkout -b recuperation <hash>
```
Git garde les commits « perdus » au moins 2 semaines.

### J'ai supprimé un fichier par erreur dans IntelliJ (jamais commité)

Clic droit sur le dossier → **Local History → Show History**, trouve le fichier, clic droit → **Revert**.

### `git push` est refusé (`rejected`, `non-fast-forward`)

Quelqu'un a poussé sur la même branche entre-temps, ou tu as fait un rebase.
- Sur **ta** branche après un rebase : `git push --force-with-lease`
- Sinon : `git pull --rebase` puis `git push`

### Des fichiers `out/` ou `.class` apparaissent dans `git status`

Ce sont des fichiers compilés : **ne les ajoute jamais**. Ils doivent être dans `.gitignore`. S'ils sont déjà suivis par Git :
```bash
git rm -r --cached out
git commit -m "chore: retire out/ du suivi git"
```

### `fatal: not a git repository`

Tu n'es pas dans le dossier du projet : `cd ~/IdeaProjects/Architecture-API-JeuDeRole`

### Vim s'est ouvert et je ne sais pas en sortir

Tu as oublié `-m` dans `git commit`.
- Pour sauvegarder : tape le message sur la première ligne, puis `Échap`, `:wq`, `Entrée`.
- Pour annuler : `Échap`, `:q!`, `Entrée`.

---

## 12. Pièges de Git Bash sur Windows

- **`bash: $'\302\203': command not found`** — un caractère invisible a été collé avec la commande. Retape le début de la ligne à la main au lieu de coller.
- **`syntax error near unexpected token '('`** — tu as collé une ligne de commentaire (`# ...`) contenant des parenthèses. Ne colle que les commandes, pas les lignes qui commencent par `#`.
- **Une faute de frappe sur la première commande d'un bloc collé** (ex. `ggit`) : les suivantes s'exécutent quand même. Relis la sortie **ligne par ligne** avant de passer à la suite.
- **Coller plusieurs commandes d'un coup** : si l'une échoue, les autres continuent. Pour les opérations risquées (`reset`, `push --force-with-lease`, `--delete`), colle **une commande à la fois**.

---

## 13. Aide-mémoire

### Commandes de tous les jours

```bash
git status                                  # où j'en suis
git log --oneline --graph -10               # derniers commits
git checkout develop && git pull            # develop à jour
git checkout -b feature/12-description      # nouvelle branche
git add chemin/Fichier.java                 # préparer un fichier
git commit -m "type(portée): description"   # commiter
git fetch origin && git rebase origin/develop  # se mettre à jour
git push -u origin feature/12-description   # premier push
git push --force-with-lease                 # push après rebase/amend
git branch -D feature/12-description        # supprimer en local (après merge)
git fetch --prune                           # nettoyer les branches distantes supprimées
```

### Ce qu'on ne fait JAMAIS

- ❌ Pousser directement sur `main` ou `develop`
- ❌ `git push --force` (sans `-with-lease`)
- ❌ Rebase / reset / amend sur `main`, `develop` ou une branche partagée
- ❌ Uploader des fichiers via l'interface web de GitHub (« Add files via upload »)
- ❌ Commiter `out/`, `.class`, ou des fichiers de test temporaires
- ❌ Une branche à son nom qui vit toute la session
- ❌ Une PR fourre-tout qui touche à 5 sujets

### Paramètres du repo (à faire une fois, par le propriétaire)

- **Settings → General → Default branch** : `develop`
- **Settings → General → Pull Requests** :
    - ✅ Allow squash merging (pour les PR de travail)
    - ✅ Allow merge commits (seulement pour les releases `develop` → `main` et les synchronisations après hotfix)
    - ✅ Automatically delete head branches
- **Settings → Branches → Add rule** pour `main` et `develop` :
    - ✅ Require a pull request before merging
    - Nombre d'approbations : 1 si l'équipe répond aux revues, sinon 0 (GitHub interdit d'approuver sa propre PR)

---

*Références : présentation « Travailler en équipe avec Git » (cours), [conventionalcommits.org](https://www.conventionalcommits.org/), [git-scm.com/book](https://git-scm.com/book/fr/v2), [docs.github.com](https://docs.github.com/fr).*