<template>
  <a-drawer
    :title="title"
    :maskClosable="true"
    width="45%"
    placement="right"
    v-if="visible"
    :closable="true"
    @close="handleCancel"
    :visible="visible"
    style="overflow: auto;padding-bottom: 53px;">
  <a-form-model
    ref="ruleForm"
    :model="form"
    :rules="rules"
    :label-col="labelCol"
    :wrapper-col="wrapperCol"
  >
    <a-form-model-item ref="name" label="字" prop="name">
<!--      <a-input-->
<!--        v-model="form.name"-->
<!--        @blur="-->
<!--          () => {-->
<!--            $refs.name.onFieldBlur();-->
<!--          }-->
<!--        "-->
<!--      />-->
      <span>
        {{wordInfo.word.words}}
      </span>
    </a-form-model-item>

    <a-form-model-item label="类型" prop="type">
<!--      <a-checkbox-group v-model="form.type">-->
<!--        <a-checkbox value="1" name="type">-->
<!--          全拼写-->
<!--        </a-checkbox>-->
<!--        <a-checkbox value="2" name="type">-->
<!--          简拼写-->
<!--        </a-checkbox>-->
<!--      </a-checkbox-group>-->
      <a-radio-group v-model="form.type">
        <a-radio value="1">
          全码
        </a-radio>
        <a-radio value="2">
          简码
        </a-radio>
        <a-radio value="3">
          全选
        </a-radio>
      </a-radio-group>
    </a-form-model-item>
    <a-form-model-item label="反馈类型" prop="feedBackType">
      <a-radio-group v-model="form.feedBackType">
        <a-radio value="1">
          结构
        </a-radio>
        <a-radio value="2">
          笔画
        </a-radio>
        <a-radio value="3">
          其他
        </a-radio>
      </a-radio-group>
    </a-form-model-item>
    <a-form-model-item label="备注" prop="desc">
      <a-input v-model="form.desc" type="textarea" />
    </a-form-model-item>
    <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">
        确认
      </a-button>
      <a-button style="margin-left: 10px;" @click="resetForm">
        重置
      </a-button>
    </a-form-model-item>
  </a-form-model>
  </a-drawer>
</template>
<script>
  import { putAction } from '../../../api/manage'

  export default {
    data() {
      return {
        form: this.getRouter(),
        visible: false,
        title: '反馈信息',
        labelCol: { span: 4 },
        wrapperCol: { span: 14 },
        other: '',
        rules: {
          name: [
            { required: false },
          ],
          type: [
            {
              required: true,
              message: '请选择类型',
              trigger: 'change',
            },
          ],
          feedBackType: [
            {
              required: true,
              message: '请选择反馈类型',
              trigger: 'change',
            },
          ],
          desc: [{ required: false }],
        },
      };
    },
    props:['wordInfo'],
    methods: {
      getRouter() {
        return {
          name: '',
          type: [],
          feedBackType:[],
          desc: '',

        }
      },
      //关闭弹窗
      handleCancel() {
        this.close()
      },
      close() {
        this.reset()
        this.$emit('close')
        this.$refs['ruleForm'].resetFields()
        this.visible = false
      },
      reset() {
        this.expandedKeysss = []
        this.checkedKeys = []
        this.defaultCheckedKeys = []
        this.loading = false
      },
      onSubmit() {
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            // alert('submit!');
            var userWord = this.wordInfo.userWord;
            userWord.ifChecked = 1;
            userWord.ifPassed = 0;
            userWord.feedbackType = this.form.type;
            userWord.feedbackRemark = this.form.desc;
            userWord.feedbackWrongType = this.form.feedBackType;
            putAction(`/words/znUserWords/edit`,userWord).then(res=>{
              if(res.success){
                this.$emit('feebackResult',true);
                this.close();
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm() {
        this.$refs.ruleForm.resetFields();
      },
      show(router) {
        if (router) {
          router.status=Boolean(router.status)
          this.router = router
        } else {
          this.router = this.getRouter()
          this.inputValue=''
        }
        this.visible = true
        this.currentTagIndex=-1
        this.currentNameIndex=-1
      },
    },
  };
</script>