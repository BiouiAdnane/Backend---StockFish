# 🐟 Backend - StockFish - Gestion de Stock pour l'Industrie des Conserves de Poisson

## 🚀 Introduction

**Backend - StockFish** est un projet développé avec **Spring Boot** pour créer une application web de gestion de stock, spécifiquement conçue pour les entreprises spécialisées dans la production de conserves de poisson. Cette application permet une gestion optimisée et détaillée des utilisateurs, des dépôts, des articles, des fournisseurs, et bien plus encore.

## 🛠️ Fonctionnalités Principales

### 👥 Gestion des Utilisateurs
L'application permet d'administrer les utilisateurs avec différents rôles et niveaux d'accès, garantissant une gestion sécurisée et hiérarchisée du stock.

### 🏢 Gestion des Dépôts
La gestion des dépôts est au cœur de l'application. Elle offre une vue complète sur l'inventaire des différents dépôts, que ce soit pour les emballages ou les produits finis.

### 🛒 Gestion des Articles
L'application fournit des outils pour gérer les articles en détail, y compris :
- **Familles d'Articles** : Classer les articles par marque, ingrédient, qualité, et nature du poisson.
- **Stockage** : Attribuer des emplacements précis pour chaque article dans les dépôts.

### 🔗 Gestion des Fournisseurs
Maintenir un suivi rigoureux des fournisseurs et des transactions effectuées avec eux.

### 🗃️ Gestion des Familles d'Articles
Les familles d'articles sont classées selon plusieurs critères :
- **Marque**
- **Ingrédient**
- **Qualité**
- **Nature du poisson**

### 📦 Gestion du Stock Optimisée
Une attention particulière est portée à l'optimisation du stockage. Lors de chaque opération d'entrée dans un dépôt, l'application permet de choisir le bloc de stockage optimal, basé sur une distribution spécifique des allées, des rangées, et des niveaux.

## 🗺️ Distribution des Allées, Rangées et Niveaux

L'application organise le stockage dans les dépôts en trois dimensions : les **allées**, les **rangées**, et les **niveaux**. Chaque combinaison de ces trois éléments désigne un emplacement précis pour un bloc de stockage.

![Classification des Dépôts](./image/Depot-classification.png)

- **Allées** : Représentent les sections principales du dépôt (par exemple, Allée 1, Allée 2, etc.).
- **Rangées** : Désignent les colonnes ou les divisions horizontales à l'intérieur de chaque allée (par exemple, Rangée B, Rangée C, etc.).
- **Niveaux** : Indiquent la hauteur ou le niveau spécifique où un article est stocké (par exemple, Niveau 1, Niveau 2, etc.).

Cette classification permet une gestion extrêmement précise et optimisée du stock, assurant que chaque article est facilement localisable et accessible.

## 🔗 Lien vers la Partie Frontend

👉 Pour une expérience complète, consulte également la partie **frontend** de l'application : [Frontend - StockFish](https://github.com/BiouiAdnane/Frontend---StockFish).

