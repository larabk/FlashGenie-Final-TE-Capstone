import axios from 'axios';

export default {

    getAllCards(id){
        return axios.get(`decks/${id}/cards`);
    },

    create(id, card){
        return axios.post(`decks/${id}/cards`, card);
    }

//   login(user) {
//     return axios.post('/login', user)
//   },

//   register(user) {
//     return axios.post('/register', user)
//   }

}