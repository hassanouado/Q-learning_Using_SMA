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
L’exemple de jeu suivant vous aidera à comprendre le concept de Q-learning :

### Initialisation
Votre agent, lorsqu’il jouera pour la première fois au jeu, n’aura aucune connaissance. Nous supposerons donc que la table Q est égale à zéro.

2. Exploration ou exploitation
Au cours de cette étape, votre agent choisira n’importe qui parmi les deux possibilités. Si l’agent exploite, il recueillera des informations à partir de la table des questions, ou lorsqu’il explore, il essaiera de trouver de nouveaux moyens.
– Lorsque votre agent travaille pour un nombre plus élevé pendant un certain temps, il est essentiel d’exploiter.
– Lorsque votre agent n’a aucune expérience, l’exploration est essentielle.
Vous pouvez gérer les ajustements entre deux conditions, l’exploration et l’exploitation, en ajoutant un epsilon. Incluez l’epsilon dans la fonction de valeur. Lorsque nous commençons avec le modèle et que nous n’incluons aucune information, vous devez préférer l’exploration. Cependant, une fois que votre modèle commence à s’adapter à l’environnement, vous devez suivre l’exploitation. En termes simples, l’agent prendra des mesures à l’étape deux, et les choix sont l’exploration et l’exploitation.

3. Mesurer la récompense
Lorsque l’agent décide de l’action à choisir, il agit. Cela conduit l’agent à l’étape suivante, qui est l’état “S”. Dans cet état, l’agent effectue quatre actions. Chacune de ces actions dirigera l’agent vers différents scores de récompense. Par exemple, si l’agent choisit l’état 5 à partir de l’état 1, il ira plus loin en fonction de l’expérience de cet état. L’agent peut alors choisir de passer à l’état 6 ou à l’état 9 en fonction de l’expérience antérieure et de l’éventuelle attente de récompense.

4. Mise à jour du tableau Q
L’agent calculera la valeur de la récompense. L’algorithme utilisera l’équation de Bellman pour mettre à jour la valeur à l’État “S”. Voici quelques terminologies
Taux d’apprentissage – Le taux d’apprentissage est une constante qui détermine le poids que vous devez ajouter dans la table des questions pour générer une nouvelle valeur au lieu de l’ancienne.
Taux d’actualisation – Le taux d’actualisation est la constante. Il permet d’escompter ce que sera la future récompense. En termes simples, le taux d’actualisation aide à équilibrer l’effet des récompenses à venir sur les nouvelles valeurs.
Une fois que l’agent a franchi toutes ces étapes en apprenant de manière significative, il obtiendra des valeurs actualisées sur la table Q. Maintenant, il est simple d’utiliser la table Q pour cartographier les états. Chaque agent d’état sélectionnera une action qui le mènera à l’état ayant la valeur Q la plus élevée.
