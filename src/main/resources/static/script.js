const baseURL = "/api/game";

document.getElementById("startBtn").addEventListener("click", async () => {
    const city = document.getElementById("cityInput").value;
    try {
        const response = await fetch(`${baseURL}/start?city=${city}`, {method: "POST"});
        const message = await response.text();
        logMessage(message);
    } catch (error) {
        logMessage(`Ошибка: ${error.message}`);
    }
});

document.getElementById("submitBtn").addEventListener("click", async () => {
    const city = document.getElementById("cityInput").value;
    try {
        const response = await fetch(`${baseURL}/process?city=${city}`, {method: "POST"});
        const message = await response.text();
        logMessage(message);
    } catch (error) {
        logMessage(`Ошибка: ${error.message}`);
    }
});

document.getElementById("resetBtn").addEventListener("click", async () => {
    try {
        const response = await fetch(`${baseURL}/reset`, {method: "POST"});
        const message = await response.text();
        logMessage(message);
    } catch (error) {
        logMessage(`Ошибка: ${error.message}`);
    }
});

function logMessage(message) {
    const logArea = document.getElementById("logArea");
    logArea.innerHTML += `<p>${message}</p>`;
    logArea.scrollTop = logArea.scrollHeight;
}
