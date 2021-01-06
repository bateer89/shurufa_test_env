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
    <a-form-model-item ref="name" label="Activity name" prop="name">
      <a-input
        v-model="form.name"
        @blur="
          () => {
            $refs.name.onFieldBlur();
          }
        "
      />
    </a-form-model-item>

    <a-form-model-item label="类型" prop="type">
      <a-checkbox-group v-model="form.type">
        <a-checkbox value="1" name="type">
          全拼写
        </a-checkbox>
        <a-checkbox value="2" name="type">
          简拼写
        </a-checkbox>
      </a-checkbox-group>
    </a-form-model-item>
    <a-form-model-item label="备注" prop="desc">
      <a-input v-model="form.desc" type="textarea" />
    </a-form-model-item>
    <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">
        Create
      </a-button>
      <a-button style="margin-left: 10px;" @click="resetForm">
        Reset
      </a-button>
    </a-form-model-item>
  </a-form-model>
  </a-drawer>
</template>
<script>
  export default {
    data() {
      return {
        visible: false,
        form: this.getRouter(),
        title: '反馈信息',
        labelCol: { span: 4 },
        wrapperCol: { span: 14 },
        other: '',
        rules: {
          name: [
            { required: true, message: 'Please input Activity name', trigger: 'blur' },
          ],
          type: [
            {
              type: 'array',
              required: true,
              message: '请选择类型',
              trigger: 'change',
            },
          ],
          desc: [{ required: false }],
        },
      };
    },
    methods: {
      getRouter() {
        return {
          name: '',
          type: [],
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
            alert('submit!');
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