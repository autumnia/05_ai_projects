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
        const response = await axios.get(`/ai/ask`, { params: { message: question } });
        responseDiv.innerText = response.data;
    }
    catch (error) {
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
    const response = await axios.get(`/ai/ask-ai`, { params: { message: question } });
    responseDiv.innerText = response.data;
} catch (error) {
    console.error('Error:', error);
    responseDiv.innerText = '오류가 발생했습니다.';
}
}