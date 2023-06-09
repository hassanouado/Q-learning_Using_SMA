# Qlearning_Algo_With_SMA
## c'est quoi Q-learning Algorithme 
Q-learning est une technique d'apprentissage par renforcement qui permet à un agent d'apprendre à prendre des décisions dans un environnement basé sur des récompenses. L'agent utilise une table de valeurs Q pour estimer la valeur attendue d'une action dans un état donné. Cette table est mise à jour à chaque itération de l'algorithme en utilisant la formule de mise à jour Q.

    ![1_4u2GtNnMa9xso1WkLh7hVA](https://github.com/hassanouado/Q-learning_Using_SMA/assets/95369534/8207c6f9-874c-4943-8008-b266686b6c3c)

## Quelques Références 
Taux d’apprentissage : lr, souvent appelé alpha, peut être défini comme le degré d’acceptation de la nouvelle valeur par rapport à l’ancienne. Ci-dessus, nous prenons la différence entre la nouvelle et l’ancienne valeur, puis nous multiplions cette valeur par le taux d’apprentissage. Cette valeur est ensuite ajoutée à notre valeur q précédente, ce qui la fait évoluer dans la direction de notre dernière mise à jour.

Gamma : gamma ou γ est un facteur d’actualisation. Il est utilisé pour équilibrer la récompense immédiate et future. Dans notre règle de mise à jour ci-dessus, vous pouvez voir que nous appliquons la décote à la récompense future. En général, cette valeur peut varier entre 0,8 et 0,99.

Récompense : la récompense (reward) est la valeur reçue après avoir effectué une certaine action à un état donné. Une récompense peut survenir à n’importe quel pas de temps donné ou seulement au pas de temps terminal

Max : np.max() utilise la bibliothèque numpy et prend le maximum de la récompense future et l’applique à la récompense de l’état actuel. Cela a pour effet d’influencer l’action actuelle par la récompense future possible. En effet, grâce au Q-learning, nous sommes capables d’allouer la récompense future aux actions actuelles pour aider l’agent à sélectionner l’action la plus rentable à tout état donné.

