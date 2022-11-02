export default [{
    id: "cockpit-easysearch-plugin",
    pluginPoint: "cockpit.dashboard",
    priority: 1,
    render: (container, additionalData) => {
        console.log(additionalData);
        const cockpitApi = additionalData.api.cockpitApi;
        const resourceLocation = cockpitApi + "/plugin/cockpit-easysearch-plugin/static/app/search-form.html";
        fetch(resourceLocation).then(response => response.text()
            .then(html => container.innerHTML = html)).then(() => {
            const searchButton = document.getElementById("search-button");
            const testInput = document.getElementById("test-var-input");
            searchButton.addEventListener("click", () => {
                const location = window.location.pathname;
                const url = location + `#/processes?searchQuery=%5B%7B"type":"PIvariables","operator":"eq","value":"${testInput.value}","name":"testVariable"%7D%5D&page=1`;
                window.location.href = url;
                window.location.href = url;
            });
        });
        console.log(resourceLocation);
    }
}]