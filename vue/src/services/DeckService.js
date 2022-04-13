import axios from 'axios';

const http = axios.create({baseURL: "http://localhost:3000"});
export default {

    getAllDecks(){
        return http.get('/decks');
    },

    create(deck){
        return http.post(`/decks/`, deck);
    }

//   register(user) {
//     return axios.post('/register', user)
//   }

}
