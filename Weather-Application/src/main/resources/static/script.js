function getWeather() {
    const city = document.getElementById("city").value.trim();
    const result = document.getElementById("result");

    if (city === "") {
        result.innerHTML = "<p>Please enter a city name</p>";
        return;
    }

    result.innerHTML = "<p>Loading...</p>";

    fetch(`http://localhost:8080/weather/${city}`)
        .then(res => res.json())
        .then(data => {
            if (data.cod !== 200) {
                result.innerHTML = "<p>City not found</p>";
                return;
            }

            result.innerHTML = `
                <h3>${data.name}, ${data.sys.country}</h3>
                <p>🌡 Temperature: ${data.main.temp} °C</p>
                <p>🤗 Feels Like: ${data.main.feels_like} °C</p>
                <p>💧 Humidity: ${data.main.humidity}%</p>
                <p>🌬 Wind Speed: ${data.wind.speed} m/s</p>
                <p>🌥 ${data.weather[0].description}</p>
            `;
        })
        .catch(() => {
            result.innerHTML = "<p>Server error</p>";
        });
}
