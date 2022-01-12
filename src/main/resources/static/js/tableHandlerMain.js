

function initializeTable() {
    let id = $('body').attr('id');
    let template_table_header = document.createElement("TR"); //this varies per id. Serves for easier reference only.
    let template_table_record = document.createElement("TR"); //this varies per id. Serves for easier reference only.






    let table_raw = $("#common_table")
    let table_thead = table_raw.find('thead').eq(0)
    let table_tbody = table_raw.find('tbody').eq(0);
    let table_data = "";
    let dictionary_map = [];
    let json_parsed = {};
    let json_raw = "";

    // TODO deleguj wzięcie metadanych tabeli do SQL za pomocą AJAX
    /*
     PHP: mając wyniki kwerendy należy użyć mysql_fetch_field lub mysql_fetch_assoc (1. iteracja)
     https://stackoverflow.com/questions/1853094/how-to-get-the-columns-names-along-with-resultset-in-php-mysql
     Potem wysłać oba jako json. (Osobno czy razem?)
     */

    json_raw = getTableJsonData(id);
    try {
         json_parsed= JSON.parse(json_raw);
    } catch (e) {
        console.error("Error during parsing JSON data: "+e);
        return false;
    }

    


    //clear the tables for production
    table_thead.children().empty();
    table_tbody.children().empty();

    //split to header and data

    dictionary_map = json_parsed.table[0];
    table_data = json_parsed.table[1];

    //ES6: użycie pętli for of.
    for (const tHeader of dictionary_map) {
        insertTableHeader(table_thead,tHeader);
    }

    let cellCount = dictionary_map.length;

    for (let i=0;i<cellCount;i++) {
        template_table_record.insertCell(-1);
    }


    populateHeaders(table_thead,template_table_header);
    populateData(table_tbody,template_table_record,dictionary_map,table_data);
    
    
}

function getTableJsonData(id) {
    if (id==="table_fruit_body") {
        return fetchFruits();
    } else if (id==="table_orders_body") {
        return fetchOrders();
    } else if (id==="table_clients_body") {
        return fetchClients();
    }
}

function populateHeaders(table_thead, template_table_header) {
    table_thead.append(template_table_header);
}

function populateData(table_tbody,template_table_record, dictionary_map, table_data) {

    for (const record of table_data) {
        let table_record = template_table_record.cloneNode(true);
        for(let i=0;i<dictionary_map.length;i++) {
            //get the cell and the string
            let cell = table_record.cells[i];
            let headerString = dictionary_map[i];

            for (const property in record) { //this loop is NOT sequential
                if (property == headerString) {
                    cell.innerHTML = record[property];
                    break;
                }
            }
        }
        table_tbody.append(table_record);
    }
}

function insertTableHeader(table_header,string_id) {
    var tHeader = document.createElement("TH");
    var tText = document.createTextNode(string_id);
    tHeader.appendChild(tText);
    table_header.append(tHeader);
}

function fetchOrders() {
    //stub
    return `{
  "table": [
    [
      "ID",
      "Zamawiajacy",
      "Klient",
      "Koszt",
      "Waga",
      "Owoc"
    ],
    [
      {
        "ID": 1,
        "Zamawiajacy": "Adam Nowak",
        "Klient": "FruitPol SA",
        "Koszt": 5000,
        "Waga": 1000,
        "Owoc": "Malina Jakość I"
      }
    ]
  ]
}`;
}
function fetchClients() {
    //stub
    return `{
  "table": [
    [
      "ID",
      "Imię i Nazwisko",
      "Owoc",
      "Cena skupu",
      "Waga",
      "Miara",
      "Data"
    ],
    [
      {
        "ID": 1,
        "Waga": 400,
        "Owoc": "Malina Pulpa",
        "Imię i nazwisko": "Adam Nowak",
        "Cena skupu": 4.78,
        "Miara": "kg",
        "Data": "2021-07-15"
      }
    ]
  ]
}`;
}
function fetchFruits() {
    //stub
    return `{
  "table": [
    [
      "ID",
      "Owoc",
      "Waga",
      "Miara",
      "Cena skupu",
      "Cena sprzedaży"
    ],
    [
      {
        "ID": 1,
        "Waga": 3500,
        "Owoc": "Malina Pulpa",
        "Cena skupu": 2.7,
        "Miara": "kg",
        "Cena sprzedaży": 2.5
      },
      {
        "ID": 2,
        "Owoc": "Malina Jakość I",
        "Cena skupu": 4.78,
        "Cena sprzedaży": 4.9,
        "Waga": 3500,
        "Miara": "kg"
      }
    ]
  ]
}`;
}

