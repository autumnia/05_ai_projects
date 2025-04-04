async function getResponse() {
      const question = document.getElementById('question').value;
      if (question.trim() === '') {
          alert('질문을 입력하세요');
          return;
      }

      const responseDiv = document.getElementById('response');
      responseDiv.innerText = '응답이 생성중입니다...';
      responseDiv.style.display = 'block';

      try {
          const response = await fetch(`/ai/ask?message=${encodeURIComponent(question)}`);
          const result = await response.text();
          responseDiv.innerText = result;
      } catch (error) {
          console.error('Error:', error);
          responseDiv.innerText = '오류가 발생했습니다.';
      }
  }

  async function getResponseOptions() {
      const question = document.getElementById('question').value;
      if (question.trim() === '') {
          alert('질문을 입력하세요');
          return;
      }

      const responseDiv = document.getElementById('response');
      responseDiv.innerText = '응답이 생성중입니다...';
      responseDiv.style.display = 'block';

      try {
          const response = await fetch(`/ai/ask-ai?message=${encodeURIComponent(question)}`);
          const result = await response.text();
          responseDiv.innerText = result;
      } catch (error) {
          console.error('Error:', error);
          responseDiv.innerText = '오류가 발생했습니다.';
      }
  }