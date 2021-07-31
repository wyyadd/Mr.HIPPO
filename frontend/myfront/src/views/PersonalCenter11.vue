<template>
    <div class="正文">
        <PersonalMenu ref="personalmeun_ins"></PersonalMenu>
        <div class="右侧正文">

        </div>
    </div>
</template>

<script>
import PersonalMenu from './PersonalMenu.vue'
export default {
  components: {
      PersonalMenu
  },
}
</script>

<style scoped>
    .正文{
        margin: auto;
        width: 100%;
    }
    .右侧正文{
        float:left;
        width: 80%;
        display:inline
    }

</style>