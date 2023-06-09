# Qlearning_Algo_With_SMA
## c'est quoi Q-learning Algorithme 
Q-learning est une technique d'apprentissage par renforcement qui permet à un agent d'apprendre à prendre des décisions dans un environnement basé sur des récompenses. L'agent utilise une table de valeurs Q pour estimer la valeur attendue d'une action dans un état donné. Cette table est mise à jour à chaque itération de l'algorithme en utilisant la formule de mise à jour Q.

   ![1_4u2GtNnMa9xso1WkLh7hVA](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/8207c6f9-874c-4943-8008-b266686b6c3c)

## Quelques Références 
Taux d’apprentissage : lr, souvent appelé alpha, peut être défini comme le degré d’acceptation de la nouvelle valeur par rapport à l’ancienne. Ci-dessus, nous prenons la différence entre la nouvelle et l’ancienne valeur, puis nous multiplions cette valeur par le taux d’apprentissage. Cette valeur est ensuite ajoutée à notre valeur q précédente, ce qui la fait évoluer dans la direction de notre dernière mise à jour.

Gamma : gamma ou γ est un facteur d’actualisation. Il est utilisé pour équilibrer la récompense immédiate et future. Dans notre règle de mise à jour ci-dessus, vous pouvez voir que nous appliquons la décote à la récompense future. En général, cette valeur peut varier entre 0,8 et 0,99.

Récompense : la récompense (reward) est la valeur reçue après avoir effectué une certaine action à un état donné. Une récompense peut survenir à n’importe quel pas de temps donné ou seulement au pas de temps terminal

Max : np.max() utilise la bibliothèque numpy et prend le maximum de la récompense future et l’applique à la récompense de l’état actuel. Cela a pour effet d’influencer l’action actuelle par la récompense future possible. En effet, grâce au Q-learning, nous sommes capables d’allouer la récompense future aux actions actuelles pour aider l’agent à sélectionner l’action la plus rentable à tout état donné.

## Processus d’apprentissage du Q-Learning

### Initialisation
Votre agent, lorsqu’il jouera pour la première fois au jeu, n’aura aucune connaissance. Nous supposerons donc que la table Q est égale à zéro.
![Capture4001](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/bcbbda31-6e46-43fd-b801-8989a53f9150)

### Exploration ou exploitation
Au cours de cette étape, votre agent choisira n’importe qui parmi les deux possibilités. Si l’agent exploite, il recueillera des informations à partir de la table des questions, ou lorsqu’il explore, il essaiera de trouver de nouveaux moyens.
– Lorsque votre agent travaille pour un nombre plus élevé pendant un certain temps, il est essentiel d’exploiter.
– Lorsque votre agent n’a aucune expérience, l’exploration est essentielle.
Vous pouvez gérer les ajustements entre deux conditions, l’exploration et l’exploitation, en ajoutant un epsilon. Incluez l’epsilon dans la fonction de valeur. Lorsque nous commençons avec le modèle et que nous n’incluons aucune information, vous devez préférer l’exploration. Cependant, une fois que votre modèle commence à s’adapter à l’environnement, vous devez suivre l’exploitation. En termes simples, l’agent prendra des mesures à l’étape deux, et les choix sont l’exploration et l’exploitation.

### Mesurer la récompense
Lorsque l’agent décide de l’action à choisir, il agit. Cela conduit l’agent à l’étape suivante, qui est l’état “S”. Dans cet état, l’agent effectue quatre actions. Chacune de ces actions dirigera l’agent vers différents scores de récompense. 
### Mise à jour du tableau Q
L’agent calculera la valeur de la récompense. L’algorithme utilisera l’équation de Bellman pour mettre à jour la valeur à l’État “S”.
## les fonction qui j'ai utilise
 #### resetState()
 ![Capture4003](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/87127613-f3e7-47b1-87cd-9b9e4c7f4ec9)

 Cette fonction est responsable de réinitialiser les valeurs des variables stateI et stateJ à zéro.
 #### chooseAction(double epsilon)
![Capture4002](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/5e9cbbc7-69df-4212-ba68-0e25f085a241)
 Cette fonctionpermet à l'agent de choisir une action à effectuer. Elle prend un paramètre epsilon qui détermine la probabilité d'exploration. Si un nombre aléatoire généré est inférieur à epsilon, l'agent effectue une exploration et choisit une action aléatoire parmi toutes les actions disponibles. Sinon, il effectue une exploitation et choisit l'action ayant la plus grande valeur Q pour l'état actuel de l'agent.
 #### finished()
 ![Capture4003](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/cd43db65-1f70-45ad-b578-f867005dbf08)

Cette fonction "finished()" vérifie si l'agent a atteint un état final sur l'île, c'est-à-dire s'il est arrivé à une zone de récompense. 
 #### executeAction(int action)
 ![Capture4005](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/98e9bb49-74b8-409c-90c5-41c9ed1af33a)

 cette fonction utilise Math.min et Math.max pour s'assurer que les valeurs de stateI et stateJ restent dans les limites de la grille (0 à GLUtils.GRID_SIZE - 1). Cela permet de mettre à jour les coordonnées de l'état actuel de manière plus concise et lisible.
 #### printBestPath()
 ![Capture4007](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/2f6c87a7-5ca2-41a3-b3a7-b55cd316aa28)

 cette fonction  permettre d'afficher les résultats et les actions dans votre Algorithme.
 #### runQLearning()
 ![Capture4008](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/2f572854-5019-4b5c-83b3-61cb188f25e6)

 cette fonction permet d'exécuter le processus d'apprentissage par renforcement et de mettre à jour la table Q en fonction des interactions de l'agent avec l'environnement.
 #### sendQTable()
 ![Capture4009](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/349af81f-b0aa-433f-9b7b-e5cbffd15888)

 La fonction sendQTable est utilisée pour envoyer la table Q à d'autres agents qui offrent le service de Q-Learning.
 ### les resultat de l'algorithm en utilise SMA
 ![carbon (6)](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/618362ad-d6cf-4c68-9c32-c9a44263030b)

