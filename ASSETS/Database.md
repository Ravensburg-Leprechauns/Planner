# Datenbankstruktur

Firebase verwendet NoSQL-Datenbanken. Folgendes Schema wird in der Anwendung verwendet:

```JSON
{
	"users": {
		"bob": {
			"name":"Bob Mustermann",
			"shirt_number":"12",
			"groups": ["1","3"],
			"attended_events": ["0","1"]
		},
		"alice": {
			"name":"Alice Mustermann",
			"shirt_number":"21",
			"groups": ["2","3"],
			"attended_events": ["1"]
		}
	},
	"groups": {
		"0": {
			"description":"Unassigned"
		}
		"1": {
			"description":"Team"
		},
		"2": {
			"description":"Jugend"
		},
		"3": {
			"description":"Allgemein"
		}
	},
	"events": {
		"0": {
			"type":"0",
      			"group":"0",
			"title":"Ravensburg vs. Ulm",
			"description":"Spiel Ravensburg gegen Ulm",
			"date":"2017-07-09_15:00",
			"location":"Ravensburg"
		},
		"1": {
			"type":"2",
      			"group":"2",
			"title":"Weihnachtsfeier",
			"description":"Leps Weihnachtsfeier 2017",
			"date":"2017-12-15_20:00",
			"location":"Ravensburg"
		}
	},
	"event_types": {
		"0": "Heimspiel",
		"1": "Auswärtsspiel",
		"2": "Training",
		"3": "Diverse"
	},
	"event_attendees": {
		"0": ["bob"],
		"1": ["bob","alice"]
	}
}
```
