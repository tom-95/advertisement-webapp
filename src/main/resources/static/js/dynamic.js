export default {
    props: ['header'],
    template: `
     <div>
      <h4> {{ header }} </h4>
      <div v-if="ads.length === 0">You have no ads</div>
        <div v-else>
        <table>
          <tbody>
          <tr v-for="ad in ads">
            <td>{{ad.title}}</td>
            <td><button type="button" @click="deleteAd(ad.id)">Delete</button></td>
          </tr>
          </tbody>
        </table>
        </div>
    </div>
    <div>
    <h2>Create new Ad</h2>
    <input v-model="titleField" placeholder="Enter title"><br>
    <textarea v-model="descriptionField" placeholder="add description"></textarea><br>
    <input v-model="priceField" placeholder="Enter price in €"><br>
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
            price: '',
            town: '',
        }
    },
    methods: {
        getMyAds() {
            axios.get('/myAds')
                .then(response => (
                    console.log(response),
                        this.ads = response.data))
        },
        create() {
            axios.post('/create', {
                title: this.titleField,
                description: this.descriptionField,
                price: this.priceField,
                town: this.townSelector
            })
                .then((response) => {
                    this.titleField = '';
                    this.descriptionField = '';
                    this.priceField = '';
                    this.getMyAds();
                }, (error) => {
                    console.log('Could not save ad!');
                });
        },
        deleteAd(id) {
            axios.delete('/delete/' + id)
                .then((response) => {
                    console.log(response),
                        this.getMyAds()
                }, (error) => {
                    console.log('Could not delete ad!');
                })
        }
    },
    mounted: function() {
        this.getMyAds();
    }
};