<template>
  <div class="editDeckPage">
    <form @submit.prevent="updateDeck" class="update-deck-form">
      <input
        type="text"
        class="name"
        placeholder="New deck name"
        v-model="deck.name"
      />
      <input
        type="text"
        class="subject"
        placeholder="New subject"
        v-model="deck.subject"
      />
      <button type="submit">Submit</button>
      <button type="cancel" @click.prevent="cancelUpdate">Cancel</button>
      <button class="delete" @click.prevent="deleteDeck">DELETE DECK</button>
    </form>
  </div>
</template>

<script>
import deckService from "@/services/DeckService";
export default {
  data() {
    return {
      deck: {
        deckId: Number(this.$route.params.id),
        name: "",
        subject: "",
        clickCount: 0,
      },
    };
  },
  computed: {},
  methods: {
    updateDeck() {
      deckService.update(this.deck).then((response) => {
        if (response.status === 200) {
          this.$router.push("/");
        }
      });
    },
    cancelUpdate(){
      this.deck.name = '';
      this.deck.subject = '';
      this.$router.push('/');
    },
    deleteDeck(){
      if(confirm("Do you really want to delete the deck? It will delete all cards in the deck.")){
        if(confirm("Are you sure? This action cannot be undone!")){
          deckService.delete(this.deck.deckId);
          this.$router.push('/');
        }
      }
    }
  },
};
</script>

<style>
</style>