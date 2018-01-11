<template>
  <div id="app">
    <div is="sui-header">
      <sui-menu inverted fluid top fixed>
        <sui-menu-item>Camunda</sui-menu-item>
        <sui-menu-item><router-link to="/">Home</router-link></sui-menu-item>
        <sui-menu-item><router-link to="/startprocess">Start Process</router-link></sui-menu-item>
        <sui-menu-item><router-link to="/tasklist">Tasklist</router-link></sui-menu-item>
      </sui-menu>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
