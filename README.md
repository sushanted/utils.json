# utils.json
Contains json related utilities <br>

<b><a href="https://github.com/sushanted/utils.json/blob/master/src/main/java/sr/utils/json/sort/JsonSorter.java">JsonSorter</a></b> : Java based json sorter. Sorts the json alphabetically and recursively, all the maps are sorted by their keys while lists are sorted by their inner contents' json representation.

For example:

Sample unsorted json:
<pre>
{
    "name": "Sushant", 
    "address": "pune", 
    "emails": [
        "sravale@pune.com", 
        "sravale@gmail.com"
    ], 
    "phones": {
        "home": [
            9876543210, 
            9089787657
        ], 
        "office": [
            8976543579
        ]
    }
}
</pre>

Output sorted json:
<pre>
{
    "address": "pune",
    "emails": [
        "sravale@gmail.com",
        "sravale@pune.com"
    ],
    "name": "Sushant",
    "phones": {
        "home": [
            9089787657,
            9876543210
        ],
        "office": [
            8976543579
        ]
    }
}
</pre>
