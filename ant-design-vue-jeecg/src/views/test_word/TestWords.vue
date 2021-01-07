<template>
  <a-card :bordered="false" style="align-content: center">
  <div style="   margin-left:42%; width: 100px;
    height: 100px;
    font-size: 70px;
    text-align: center;
    font-family: cursive;
    border: 1px solid rgb(119, 119, 119);">{{char}}</div>
  <input style="margin-top: 20px;margin-left:39%;border: 1px solid rgb(119, 119, 119);    height: 30px;
    width: 220px;" id="testInput" type="text" placeholder="请输入" @keyup="textchange($event)" v-model="inputVal">
   <span style="margin-left:10px;"><a-icon type="close-circle" :style="{ fontSize: '23px' }"v-if="errorStatus"/>
    <a-icon type="check-circle" :style="{ fontSize: '23px' }" theme="twoTone" two-tone-color="#52c41a" v-if="successStatus"/></span>
    <div style="margin-left: 39%;margin-top: 20px"> <a-button type="primary" @click="submit" :disabled="submitStatus">提交</a-button>
    <a-button style="margin-left: 10%" type="primary" @click="feedback" > 反馈</a-button></div>
    <feeback-form ref="modalForm" @ok="modalFormOk" :wordInfo="wordInfo" @feebackResult="feedbackResult()"></feeback-form>
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
  import { getAction, putAction } from '../../api/manage'
  import debounce from 'lodash/debounce'
  // Vue.use(Element)
  export default {
    name: 'TestWords',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      FeebackForm,
    },
    data () {
      submitStatus: true;
      this.loadData = debounce(this.loadData, 800);//消抖
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
        dictOptions:{},
        superFieldList:[],
        inputVal:"",
        code:"",
        char:"",
        englishInput:"",
        chineseInput:"",
        wordInfo:"",
        errorStatus: false,
        successStatus: false,
        submitStatus: true
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
      loadData(){
        getAction(`/words/znUserWords/queryWord`).then(res=>{
          if(res.success){
            this.code = res.result.word.keyboardSequence;
            this.char = res.result.word.words;
            this.wordInfo = res.result;
          }else{
            this.$message.warning(res.message)
          }

        })

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
        var codeArray = this.code.split(",");

        if (codeArray.indexOf(this.englishInput) != -1  && this.chineseInput === this.char) {
          $('#result').val('right');
          console.log('ok!');
          this.errorStatus = false;
          this.successStatus = true;
          this.submitStatus = false;
        }else{
          this.errorStatus = true;
          this.successStatus = false;
          this.submitStatus = true;
        }

      },
      submit(){
        var userWord = this.wordInfo.userWord;
        userWord.ifChecked = 1;
        userWord.ifPassed = 1;
        userWord.input = this.englishInput;
        var that = this;
        putAction(`/words/znUserWords/edit`,userWord).then(res=>{
          if(res.success){
            this.loadData()
            this.inputVal = ''
            this.code = ''
            this.char = ''
            this.englishInput = ''
            this.chineseInput = ''
            this.wordInfo = ''
            this.errorStatus = false
            this.successStatus = false
            this.submitStatus = true
          }
        })
      },
      feedback(record){
        // record.formValue.
        console.log('Clicked cancel button');
        // this.visible = true;
        this.$refs['modalForm'].show(record)
      },

      feedbackResult(){
        this.loadData()
        this.inputVal = ''
        this.code = ''
        this.char = ''
        this.englishInput = ''
        this.chineseInput = ''
        this.wordInfo = ''
        this.errorStatus = false
        this.successStatus = false
        this.submitStatus = true
      }

    }
    
  }

</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>