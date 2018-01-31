<template>
  <form>
    <label for="email">E-Mail</label>
    <input v-validate="{ required: true, email: true, regex: /[0-9]+/ }" type="text" name="email">
    <span v-show="errors.has('email')">{{ errors.first('email') }}</span>
    <input type="submit" value="Submit">
  </form>
</template>

<script>



</script>
