<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="12">
            <a-form-item label="产品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['productName', validatorRules.productName]" placeholder="请输入产品名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="物料编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--<j-dict-select-tag type="list" v-decorator="['materialId', validatorRules.materialId]" :trigger-change="true" dictCode="" placeholder="请选择物料编号"/>-->
              <a-select
                show-search
                option-filter-prop="children"
                :filter-option="filterOption"
                v-decorator="['materialId', validatorRules.materialId]"
                :trigger-change="true"
                placeholder="请选择物料编号">
                <a-select-option v-for="(item, key) in materialList" :key="key" :value="item.id">{{item.materialName}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="产品型号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['productType']" placeholder="请输入产品型号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="产品规格" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['productSize']" placeholder="请输入产品规格"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="产品颜色" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['productColor']" placeholder="请输入产品颜色"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-textarea v-decorator="['disp']" rows="4" placeholder="请输入备注"/>
            </a-form-item>
          </a-col>
          <!--<a-col :span="12">
            <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['createBy']" placeholder="请输入创建人"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择创建时间" v-decorator="['createTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="修改人" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['updataBy']" placeholder="请输入修改人"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="修改时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择修改时间" v-decorator="['updataTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="删除标识0-正常,1-已删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['delFlag']" :trigger-change="true" dictCode="del_flag" placeholder="请选择删除标识0-正常,1-已删除"/>
            </a-form-item>
          </a-col>-->
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: 'MbpProductMapForm',
    components: {
      JFormContainer,
      JDate,
      JDictSelectTag,
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        /*物料信息*/
        materialList: [],
        form: this.$form.createForm(this),
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          productName: {
            rules: [
              {required: true, message: '请输入产品名称!'},
            ]
          },
          materialId: {
            rules: [
              {required: true, message: '请选择物料!'},
            ]
          }
        },
        url: {
          add: "/system/mbpProductMap/add",
          edit: "/system/mbpProductMap/edit",
          queryById: "/system/mbpProductMap/queryById",
          queryMaterialList: "/system/mbpMaterial/allList" // 全查询设备信息
        }
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      /*下拉框过滤方法*/
      filterOption(input, option) {
        return (
          option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
        );
      },
      /*全查询设备信息*/
      queryMaterialList() {
        getAction(this.url.queryMaterialList,null).then((res)=>{
          if(res.success){
            this.materialList.splice(0)
            this.materialList = res.result
          }
        });
      },
      add () {
        this.edit({});
      },
      edit (record) {
        this.queryMaterialList() // 查询物料信息
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'productName','materialId','productType','productSize','productColor','disp','createBy','createTime','updataBy','updataTime','delFlag'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            // console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }

        })
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'productName','materialId','productType','productSize','productColor','disp','createBy','createTime','updataBy','updataTime','delFlag'))
      },
    }
  }
</script>