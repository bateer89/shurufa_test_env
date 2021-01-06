<template>
  <a-card :bordered="false" style="align-content: center">
  <div style="   margin-left:42%; width: 100px;
    height: 100px;
    font-size: 70px;
    text-align: center;
    font-family: cursive;
    border: 1px solid rgb(119, 119, 119);">王</div>
  <input style="margin-top: 20px;margin-left:39%;border: 1px solid rgb(119, 119, 119);    height: 30px;
    width: 200px;" id="testInput" type="text" placeholder="请输入" @keyup="textchange($event)">
   <div style="margin-left: 39%;margin-top: 20px"> <a-button type="primary" @click="submit">提交</a-button>
    <a-button style="margin-left: 10%" type="primary" @click="feedback"> 反馈</a-button></div>
<!--    <a-modal title="反馈"-->
<!--             :visible="visible"-->
<!--             :confirm-loading="confirmLoading"-->
<!--             @ok="handleOk"-->
<!--             @cancel="handleCancel">-->
<!--    <a-form :form="form">-->

<!--      <a-form-model-item label="type" prop="type" v-decorator="['type', { rules: [{ required: true, message: '请选择类型!' }] }]">-->
<!--        <a-checkbox-group >-->
<!--          <a-checkbox value="1" name="type">-->
<!--            全笔画-->
<!--          </a-checkbox>-->
<!--          <a-checkbox value="2" name="type">-->
<!--            简画-->
<!--          </a-checkbox>-->
<!--        </a-checkbox-group>-->
<!--      </a-form-model-item>-->
<!--      <a-form-model-item has-feedback label="备注" prop="remark">-->
<!--        <a-input  autocomplete="off" />-->
<!--      </a-form-model-item>-->
<!--    </a-form>-->
<!--    </a-modal>-->
    <feeback-form ref="modalForm" @ok="modalFormOk"></feeback-form>
  </a-card>



</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import FeebackForm from './modules/FeebackForm'
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'
  import Vue from 'vue' 
  import $ from 'jquery'
  // Vue.use(Element)
  var char = "王";
  export default {
    name: 'TestWords',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      FeebackForm,
    },
    data () {
      return {
        form: this.$form.createForm(this, { name: 'coordinated' }),
        description: '汉字对照表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          }
        ],
        url: {
          list: "/words/znWords/list",
          delete: "/words/znWords/delete",
          deleteBatch: "/words/znWords/deleteBatch",
          exportXlsUrl: "/words/znWords/exportXls",
          importExcelUrl: "words/znWords/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
        inputVal:"",
        code:"yyuy",
        char:"王",
        ModalText: 'Content of the modal',
        visible: false,
        confirmLoading: false,
        englishInput:"",
        chineseInput:""
      }
    },
    created() {
      $(function(){
        $("#testId").val("");
        $("#result").val("wrong");
        $("#charResult").val("wrong");
    });
    },
    computed: {
      // importExcelUrl: function(){
      //   return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      // },
    },
    methods: {
      init(){
		    
	    },
      textchange() {

        var temp = $('#testInput').val()
        var pattern = new RegExp("[\u4E00-\u9FA5]+");
        if (pattern.test(temp)) {
          this.chineseInput = $('#testInput').val()
        } else {
          this.englishInput = $('#testInput').val()
        }
        console.log( this.englishInput)
        console.log( this.chineseInput)
        if (this.englishInput === this.code  && this.chineseInput === this.char) {
          $('#result').val('right')
          console.log('ok!')
        }

      },
      submit(){

      },
      feedback(record){
        console.log('Clicked cancel button');
        // this.visible = true;
        this.$refs['modalForm'].show(record)
      },
      handleOk(e){
        e.preventDefault();
        this.form.validateFields((err, values) => {
          console.log(err);
          if (!err) {
            console.log('Received values of form: ', values);
          }else{
            this.confirmLoading = true;
            setTimeout(() => {
              this.visible = false;
              this.confirmLoading = false;
            }, 2000);
          }
        });


      },
      handleCancel(){
        this.visible = false;
      }
    }
    
  }

</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>