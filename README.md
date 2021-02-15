# **Metodologie di Programmazione**

Pierpaolo D&#39;Angelo

**Introduzione**

Questo progetto si propone di modellare la base di uno shop online, all&#39;interno del quale sono rappresentati dei prodotti, che possono essere o degli articoli o dei raggruppamenti eventualmente scontati di altri prodotti.

Sono presenti, inoltre, una serie di meccanismi che offrono la possibilità di manipolare e organizzare insiemi di prodotti, ad esempio:

- Monitorare il prezzo di un prodotto nel tempo.
- Monitorare il numero di prodotti di un certo tipo nel tempo.
- Applicare uno sconto aggiuntivo se il numero di articoli di un certo tipo è superiore a una certa soglia.
- Estrarre il prezzo maggiore e minore dei prodotti di un determinato tipo.

Pattern utilizzati all&#39;interno del progetto:

- Composite
- Observer
- Visitor
- Strategy
- Factory

**Scelte implementative**

**Composite**

Per la modellazione della gerarchia dei prodotti è stato scelto di utilizzare un Composite, nella sua variante Design for Uniformity. In particolare, la classe astratta _AbstractProduct_ presenta un campo codice e un campo nome ed espone, oltre ai getter, dei metodi per manipolare una lista di _AbstractProduct_. I metodi _addAllProducts_ e _removeAllProducts_ sono dei template methods, poiché si basano sull&#39;aggiunta e la rimozione singola che invece vengono lasciate da implementare alle sottoclassi.

La classe _BaseProduct_ consiste in una classe leaf, aggiunge i campi relativi a prezzo e tipo, e ridefinisce i metodi _addProduct_, _removeProduct_ e _getProducts_, i quali, rappresentando operazioni non supportate dal BaseProduct sollevano un eccezione di tipo _UnsupportedOperationException_. Viene inoltre ridefinito il metodo _getPrice_, che qui restituisce il valore mantenuto dal campo price, e aggiunto il relativo setter.

La classe _KitProduct_ consiste in una classe composite, aggiunge due campi, una _DiscountStrategy_ e una lista di _AbstractProduct_. Vengono ridefiniti i metodi _addProduct_ e _removeProduct_, i quali aggiungono e rimuovono un prodotto dalla lista e _getProducts_ che restituisce l&#39;iterator di quest&#39;ultima. Il prezzo in questa classe non è un campo, ma viene calcolato dal metodo _getPrice_ sommando i prezzi di tutti gli _AbstractProduct_ presenti nella lista dei prodotti e applicando lo sconto specificato dalla _DiscountStrategy_.

![](RackMultipart20210215-4-1q8bzg_html_61db02e7c10f02e0.png)

**Visitor**

Per poter manipolare un insieme di prodotti senza preoccuparsi della differenza tra _BaseProduct_ e _KitProduct_, viene utilizzato un Visitor.

L&#39;interfaccia _ProductVisitor_ descrive due metodi visit in overload, i quali accettano come parametro rispettivamente un _BaseProduct_ o un _KitProduct_.

Le implementazioni concrete di _ProductVisitor_ sono le seguenti:

- **DiscountProductVisitor** : questo Visitor si occupa di decidere se uno sconto deve essere applicato o meno a seconda di quanti prodotti di uno stesso tipo sono contenuti all&#39;interno di un _AbstractProduct_. Se al metodo visit viene passato un _BaseProduct_ e questo è del tipo interessato, il contatore inizializzato in costruzione viene decrementato, altrimenti, viene invocato l&#39;_accept_ su ogni elemento del _KitProduct_. Il metodo _getDiscountStrategy_ restituisce la _DiscountStrategy_ passata in costruzione se si è raggiunto il numero di prodotti specificato, altrimenti la _DiscountStrategy_._NO\_DISCOUNT_ (che non applica nessuno sconto).
- **HiLoPriceVisitor** : questo Visitor si occupa, dato un determinato tipo, di determinare il prezzo più alto e più basso dei prodotti contenuti in un _AbstractProduct_. Se al metodo _visit_ viene passato un _BaseProduct_ del tipo interessato e il prezzo corrisponde al massimo o al minimo incontrato, la rispettiva variabile di istanza viene aggiornata, altrimenti, viene invocato l&#39;_accept_ su ogni elemento del _KitProduct_.
- **TypeCounterVisitor** : questo Visitor si occupa di contare il numero di prodotti di un determinato tipo all&#39;interno di un _AbstractProduct_. Se al metodo _visit_ viene passato un _BaseProduct_ del tipo interessato il contatore viene incrementato, altrimenti, viene invocato l&#39;_accept_ su ogni elemento del _KitProduct_.

![](RackMultipart20210215-4-1q8bzg_html_53887b1de5ee24a4.png)

**Observer**

Per poter monitorare le variazioni subite nel tempo dagli _AbstractProduct_ viene usato un Observer.

La classe astratta _AbstractSubject_ ha come unico campo una lista di observer e i metodi ad essa utili. L&#39;interfaccia _IObserver_ descrive un unico metodo _update_. È stata introdotta una classe astratta intermedia _AbstractProductObserver_ che centralizza la sottoscrizione dell&#39;observer su un _AbstractProduct_.

Le implementazioni concrete di _IObserver_ sono le seguenti:

- **PriceMonitorObserver** : questo Observer monitora nel tempo le variazioni del prezzo di un _AbstractProduct_. Quando viene chiamato il metodo update, se il prezzo è cambiato rispetto a quello precedentemente salvato, aggiorna il prezzo medio e se necessario aggiorna anche il prezzo più alto e più basso.
- **TypeCounterObserver** : questo Observer mantiene uno storico di quanti prodotti di un determinato tipo sono contenuti all&#39;interno di un _AbstractProduct_. Quando viene chiamato il metodo _update_, un oggetto di tipo _TypeCounterVisitor_ viene fatto accettare all&#39;_AbstractProduct_, se il numero dei prodotti del tipo monitorato è cambiato, viene aggiornato lo storico.

![](RackMultipart20210215-4-1q8bzg_html_13019e724c1f9e41.png)

**Strategy e Factory**

Per implementare diverse strategie di sconto è stato usato uno Strategy.

L&#39;interfaccia funzionale _DiscountStrategy_ descrive un metodo _applyDiscount_ che prende in input un prezzo e ne restituisce un altro.

Gli oggetti di tipo _DiscountStrategy_ vengono creati da un&#39;apposita Factory. In _DiscountStrategyFactory_, il metodo _newAbsoluteDiscount_ costruisce una _DiscountStrategy_ che riduce il prezzo di un importo pari al parametro specificato, mentre il metodo _newPercentageDiscount_ riduce il prezzo di una percentuale passata come parametro. Infine, è anche presente il campo statico e pubblico _NO\_DISCOUNT_ che lascia inalterato il prezzo.

![](RackMultipart20210215-4-1q8bzg_html_48b4e799caeb20b0.png)

![](RackMultipart20210215-4-1q8bzg_html_2e5a44dcaf85b22a.png)

**Test**

**Composite**

**BaseProduct** :

- _getProducts_, _addProduct_, _removeProduct_: si controlla che l&#39;invocazione di questi metodi su un _BaseProduct_ lanci un&#39;eccezione di tipo _UnsupportedOperationException_.

**KitProduct** :

- Si controlla che costruendo un _KitProduct_ senza una _DiscountStrategy_ venga utilizzato NO\_DISCOUNT.
- Si controlla che costruendo un _KitProduct_ con una _DiscountStrategy_ il corrispondente sconto venga applicato.
- Vengono testati tutti i metodi di aggiunta e rimozione di un _AbstractProduct_ dalla lista products.

**Observer**

**PriceMonitorObserver** :

- Si controlla che un _PriceMonitorObserver_ appena instanziato abbia i valori corretti.
- Si controlla che chiamando il metodo _setPrice_ passando come parametro un prezzo uguale al prezzo attuale la media non venga aggiornata.
- Si controlla che dopo aver chiamato tre volte il metodo _setPrice_ con parametri significativi, la media, _lowestPrice_, _highestPrice_ e counter siano aggiornati di conseguenza.

**TypeCounterObserver** :

- Si controlla che un _TypeCounterObserver_ appena instanziato abbia i valori corretti.

- Si controlla che aggiungendo un prodotto del tipo monitorato lo storico venga aggiornato.
- Si controlla che aggiungendo un prodotto non del tipo monitorato lo storico non venga aggiornato.

**Strategy**

**DiscountStrategy** :

- Si controlla che tutti e tre i metodi forniti dalla Factory restituiscano valori corretti.
- Si controlla che passando a _newAbsoluteDiscount_ come parametro un valore più grande del prezzo, il valore restituito da _applyDiscount_ sia 0.
- Si controlla che passando a _newAbsoluteDiscount_ come parametro un valore negativo venga lanciata l&#39;eccezione di tipo _IllegalArgumentException_.
- Si controlla che passando a _newPercentageDiscount_ come parametro un valore più grande di 100 o minore di 0 venga lanciata l&#39;eccezione di tipo _IllegalArgumentException_.

**Visitor**

**DiscountProductVisitor** :

- Si controlla che il visitor restituista la _DiscountStrategy_ passata in costruzione solo se all&#39;interno dell&#39;_AbstractProduct_ sono presenti il numero minimo di prodotti di tipo scelto.

**HiLoPriceVisitor** :

- Si controlla che il visitor salvi il prezzo massimo e il prezzo minimo dei prodotti di tipo scelto presenti nell&#39;_AbstractProduct_.

**TypeCounterVisitor** :

- Si controlla che il visitor conteggi il numero corretto di prodotti del tipo scelto all&#39;interno dell&#39;_AbstractProduct_.
