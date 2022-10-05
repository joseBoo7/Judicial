var date = new Date();

// Get year, month, and day part from the date
var year = date.toLocaleString("default", { year: "numeric" });
var month = date.toLocaleString("default", { month: "2-digit" });
var day = date.toLocaleString("default", { day: "2-digit" });

// Generate yyyy-mm-dd date string
var formattedDate = year + "-" + month + "-" + day;
    
//Otorgar fecha actual a los input dates
document.getElementById("date_1").value = formattedDate;
document.getElementById("date_2").value = formattedDate;