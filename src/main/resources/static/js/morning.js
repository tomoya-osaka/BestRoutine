document.addEventListener("DOMContentLoaded", () => {
    const addBtn = document.querySelector(".add-btn");
	const formContainer = document.querySelector("form");//追加
    const submitWrapper = document.querySelector(".submit-wrapper");
  
    addBtn.addEventListener("click", () => {
      const routineItem = document.createElement("div");
      routineItem.className = "routine-item";
  
      const span = document.createElement("span");
      span.className = "routine-number";
      span.textContent = "1)"; // 仮の番号。あとで再計算される
  
      const input = document.createElement("input");
      input.type = "text";
      input.className = "routine-input";
	  input.name="items";//追加
  
      const removeBtn = document.createElement("button");
      removeBtn.className = "remove-btn";
      removeBtn.textContent = "－";
  
      removeBtn.addEventListener("click", () => {
        routineItem.remove();
        updateRoutineNumbers();
      });
  
      routineItem.appendChild(span);
      routineItem.appendChild(input);
      routineItem.appendChild(removeBtn);
  
      // 「追加」ボタンの直前に挿入（submitWrapper の前）
      formContainer.insertBefore(routineItem, addBtn.closest(".add-wrapper"));
  
      updateRoutineNumbers();
    });
  
    // 最初からある「－」ボタンにもイベントをつける
    document.querySelectorAll(".remove-btn").forEach(btn => {
      btn.addEventListener("click", (e) => {
        e.target.closest(".routine-item").remove();
        updateRoutineNumbers();
      });
    });
  
    function updateRoutineNumbers() {
      const numbers = document.querySelectorAll(".routine-number");
      numbers.forEach((span, index) => {
        // Unicodeの①〜⑩は、0x2460から始まる
        span.textContent = `${index + 1}）`;
      });
    }
  });
  
  // ✅ 入力欄のバリデーション（空送信を防止）
  function validateRoutineInputs() {
    const inputs = document.querySelectorAll('.routine-input');
    const errorMessage = document.getElementById('error-message');

    const hasValidInput = Array.from(inputs).some(input => input.value.trim() !== '');

    if (!hasValidInput) {
      errorMessage.textContent = "ルーティンを1つ以上入力してください。";
      return false;
    }

    errorMessage.textContent = "";
    return true;
  }
  