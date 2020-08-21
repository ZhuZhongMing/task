<template>
  <a-card :bordered="false">
    <a-steps class="steps" :current="currentTab">
      <a-step title="客户信息" />
      <a-step title="订单信息" />
      <a-step title="完成" />
    </a-steps>
    <div class="content">
      <MbpCustomerForm v-if="currentTab === 0" ref="MbpCustomerForm" @nextStep="nextStep"/>
      <MbpOrderModal v-if="currentTab === 1" ref="MbpOrderModal" @nextStep="nextStep" @prevStep="prevStep"/>
      <step3 v-if="currentTab === 2" ref="step3" @prevStep="prevStep" @finish="finish"/>
    </div>
  </a-card>
</template>

<script>
  import MbpCustomerForm from './form/MbpCustomerForm'
  import MbpOrderModal from './form/MbpOrderModal'
  import Step3 from './form/Step3'

  export default {
    name: "StepForm",
    components: {
      MbpCustomerForm,
      MbpOrderModal,
      Step3
    },
    data () {
      return {
        description: '将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。',
        currentTab: 0,

        // form
        form: null,
      }
    },
    methods: {
      // handler
      nextStep () {
        if (this.currentTab < 2) {
          this.currentTab += 1
          /*转至订单页面，需生成订单ID*/
          if (this.currentTab == 1) {
            this.$nextTick(function(){
              this.$refs.MbpOrderModal.genOrderId()
            })
          }
        }
      },
      prevStep () {
        if (this.currentTab > 0) {
          this.currentTab -= 1
          /*转至订单页面，需生成订单ID*/
          if (this.currentTab == 1) {
            this.$nextTick(function(){
              this.$refs.MbpOrderModal.genOrderId()
            })
          }
        }
      },
      finish () {
        this.currentTab = 0
      }
    }
  }
</script>

<style lang="less" scoped>
  .steps {
    max-width: 1600px;
    width: 80%;
    margin: 16px auto;
  }
  .content {
    max-width: 1600px;
    width: 80%;
    margin: 16px auto;
  }
</style>