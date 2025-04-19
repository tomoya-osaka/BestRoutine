function generateCalendar(year, month) {
    const calendarDiv = document.getElementById("calendar");
  
    // 曜日ヘッダー
    const days = ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"];
    let html = "<table><thead><tr>";
    days.forEach(d => {
      html += `<th>${d}</th>`;
    });
    html += "</tr></thead><tbody>";
  
    const firstDay = new Date(year, month, 1).getDay();
    const lastDate = new Date(year, month + 1, 0).getDate();
  
    let date = 1;
    for (let i = 0; i < 6; i++) {
      html += "<tr>";
      for (let j = 0; j < 7; j++) {
        if (i === 0 && j < firstDay) {
          html += "<td></td>";
        } else if (date > lastDate) {
          html += "<td></td>";
        } else {
		  const fullDate = `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`;
		  html += `<td data-date="${fullDate}">${date}</td>`;
          date++;
        }
      }
      html += "</tr>";
      if (date > lastDate) break;
    }
  
    html += "</tbody></table>";
    calendarDiv.innerHTML = html;
	
	// ✅ 達成日を色付け
	if (typeof achievementDates !== "undefined" && Array.isArray(achievementDates)) {
	  achievementDates.forEach(dateStr => {
	    const cell = document.querySelector(`td[data-date='${dateStr}']`);
	    if (cell) {
	      cell.classList.add("achieved-day");
	    }
	  });
	}
  }
  
  // 現在の年月でカレンダー生成
  const today = new Date();
  generateCalendar(today.getFullYear(), today.getMonth());