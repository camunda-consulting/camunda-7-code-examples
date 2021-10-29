import html from './hello-world.js';

export default [
	{
		id: "cockpit-route-plugin",
		pluginPoint: "cockpit.route",
		priority: 1,
		properties: {
			path: "/customRoute"
		},
		render: container => {
			container.innerHTML = html
		}
	}
	,
	{
		id: "cockpit-navigation-plugin",
		pluginPoint: "cockpit.navigation",
		priority: 5,
		properties: {
			path: "/customRoute"
		},
		render: container => {
			container.innerHTML = '<a href="#/customRoute">Custom Page</a>'
		}
	}
]