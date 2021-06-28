export default {
    props: ['header'],
    template: `
    <br>
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
    <br>
    <h2>Create new Ad</h2>
    <p><input v-model="titleField" placeholder="Enter title"></p>
    <p><textarea v-model="descriptionField" placeholder="add description"></textarea></p>
    <p><input v-model="priceField" placeholder="Enter price in €"></p>
    <p><select v-model="townSelector">
    <option disabled value="">Please select town</option>
    <option>Berlin</option>
    <option>Hamburg</option>
    <option>München</option>
    </select></p>
    <p><input type="file" @change="selectedFile"></p>
    <p><button type="button" @click="create()">Create Ad</button></p>
    </div>
    `,
    data() {
        return {
            ads: [],
            file: null
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
            const formData = new FormData();
            formData.append("title", this.titleField),
                formData.append("description", this.descriptionField),
                formData.append("price", this.priceField),
                formData.append("town", this.townSelector),
            formData.append("image", this.file, this.file.name)
            axios.post('/create', formData)
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
        },
        selectedFile(newFile) {
            this.file = newFile.target.files[0]
        }
    },
    mounted: function() {
        this.getMyAds();
    }
};