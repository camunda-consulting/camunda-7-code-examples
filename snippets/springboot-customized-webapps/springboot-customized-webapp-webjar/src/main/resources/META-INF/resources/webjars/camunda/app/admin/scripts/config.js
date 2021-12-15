export default {
  // // change the app name and vendor
  // app: {
  //   name: 'Admin',
  //   vendor: 'Company'
  // },
  // // custom libraries and scripts loading and initialization,
  // // see: http://docs.camunda.org/guides/user-guide/#tasklist-customizing-custom-scripts
  // customScripts: [
  //   // If you have a folder called 'my-custom-script' (in the 'admin' folder)
  //   // with a file called 'customScript.js' in it
  //   // 'my-custom-script/customScript'
  // ],
  // requireJsConfig: {
  //   // AngularJS module names
  //   ngDeps: ['ui.bootstrap'],
  //   // RequireJS configuration for a complete configuration documentation see:
  //   // http://requirejs.org/docs/api.html#config
  //   deps: ['jquery', 'custom-ui'],
  //   paths: {
  //     // if you have a folder called `custom-ui` (in the `admin` folder)
  //     // with a file called `scripts.js` in it and defining the `custom-ui` AMD module
  //     'custom-ui': 'custom-ui/scripts'
  //   }
  // },
  // 'runtimeActivityInstanceMetrics': {
  //   'display': true
  // },
  // 'historicActivityInstanceMetrics': {
  //   'adjustablePeriod': true,
  //   'period': {
  //     'unit': 'day'
  //   }
  // },
  'locales': {
    'availableLocales': ['${CUSTOM_LANG}', 'en'],
    'fallbackLocale': 'en'
  }
  // csrfCookieName: 'XSRF-TOKEN',
  // disableWelcomeMessage: false,
  // userOperationLogAnnotationLength: 4000
}; 