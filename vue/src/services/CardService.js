import axios from 'axios';

export default {

    getAllCards(id){
        return axios.get(`decks/${id}/cards`);
    },

    create(id, card){
        return axios.post(`decks/${id}/cards`, card);
    }


}