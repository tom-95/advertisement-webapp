const app = Vue.createApp({})

app.component('dynamic', {
    template: `
    <div>
    <h2>Here are all your ads</h2>
    <div v-if="ads.length === 0">You have no ads</div>
    <div v-else>
    <p v-for="ad in ads">{{ad.title}}</p>
    </div>
    </div>
    <div>
    <h2>Create new Ad</h2>
    <input v-model="titleField" placeholder="Enter title"><br>
    <textarea v-model="descriptionField" placeholder="add description"></textarea><br>
    <select v-model="townSelector">
    <option disabled value="">Please select one</option>
    <option>Berlin</option>
    <option>Hamburg</option>
    <option>München</option>
    </select><br>
    <button type="button" @click="create()">Create Ad</button>
    </div>
    `,
    data() {
        return {
            ads: [],
            title: '',
            description: '',
            town: '',
        }
    },
    methods: {
        getMyAds() {
            axios.get('/myAds')
                .then(response => (this.ads = response.data))
        },
        create() {
            axios.post('/create', {
                title: this.titleField,
                description: this.descriptionField,
                town: this.townSelector
            })
                .then((response) => {
                this.titleField = '';
                this.descriptionField = '';
                this.getMyAds();
            }, (error) => {
                console.log('Could not save ad!');
            });
        },
    },
    mounted: function() {
        this.getMyAds();
    }
})

app.mount('#dynamic')