<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="汉字" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['words']" placeholder="请输入汉字" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="全码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['keyboardSequence']" placeholder="请输入全码" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="简码1" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['keyboardSimpleSequence1']" placeholder="请输入简码1" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="简码2" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['keyboardSimpleSequence2']" placeholder="请输入简码2" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="简码3" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['keyboardSimpleSequence3']" placeholder="请输入简码3" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="全码笔画" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['allStrokes']" placeholder="请输入全码笔画" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="简码笔画1" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['simpleStrokes1']" placeholder="请输入简码笔画1" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="简码笔画2" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['simpleStrokes2']" placeholder="请输入简码笔画2" disabled ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="简码笔画3" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['simpleStrokes3']" placeholder="请输入简码笔画3" disabled ></a-input>
            </a-form-item>
          </a-col>
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

  export default {
    name: 'ZnWordsForm',
    components: {
      JFormContainer,
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
        },
        url: {
          add: "/words/znWords/add",
          edit: "/words/znWords/edit",
          queryById: "/words/znWords/queryById"
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
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'words','keyboardSequence','keyboardSimpleSequence1','keyboardSimpleSequence2','keyboardSimpleSequence3','allStrokes','simpleStrokes1','simpleStrokes2','simpleStrokes3'))
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
            console.log("表单提交数据",formData)
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
        this.form.setFieldsValue(pick(row,'words','keyboardSequence','keyboardSimpleSequence1','keyboardSimpleSequence2','keyboardSimpleSequence3','allStrokes','simpleStrokes1','simpleStrokes2','simpleStrokes3'))
      },
    }
  }
</script>