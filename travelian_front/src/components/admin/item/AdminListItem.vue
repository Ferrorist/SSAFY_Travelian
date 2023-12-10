<script setup>
import { ref } from "vue";

defineProps({ user: Object });
</script>

<template>
  <tr class="user_column">
    <td class="align-middle">{{ user.userId }}</td>
    <td class="align-middle">{{ user.name }}</td>
    <td class="align-middle">{{ user.emailId }}@{{ user.emailDomain }}</td>
    <td class="align-middle" v-if="user.isAdmin === '0'">
      <img src="@/assets/images/admin_false.png" />
    </td>
    <td class="align-middle" v-else>
      <img src="@/assets/images/admin_true.png" />
    </td>
  </tr>
</template>

<style scoped></style>
