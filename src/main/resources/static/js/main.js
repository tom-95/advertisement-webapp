import Dynamic from './dynamic.js';

const app = Vue.createApp({});
app.component('dynamic', Dynamic);
app.mount('#app');