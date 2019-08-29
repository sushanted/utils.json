# utils.json
All json related utilities
JsonSorter : Java based json sorter. Sorts the json alphabetiacally and recursively, all the maps are sorted by their keys while lists are sorted by their inner content.

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
