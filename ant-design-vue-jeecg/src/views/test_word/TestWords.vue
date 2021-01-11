<template>
  <a-card :bordered="false" style="align-content: center">
    <div style="display:flex">
    <div style="   margin-left:42%; width: 100px;
    height: 100px;
    font-size: 70px;
    text-align: center;
    font-family: cursive;
    border: 1px solid rgb(119, 119, 119);">{{char}}
    </div>
    <div style="font-size: 25px;margin-left: 40%;">
      进度：{{lossCount}}/{{allCount}}
    </div>
    </div>
    <a-form :form="form" :wrapper-col="{ span: 12, offset: 5 }" style="margin-top:10px">
      <a-form-item  label="全码" prop="full" has-feedback :label-col="{ span: 7 }" :wrapper-col="{ span: 8 }" >
        <a-input @keyup="fullChange($event)" v-model="fullInput"
                 v-decorator="['full',validatorRules.full]"></a-input>
      </a-form-item>
      <a-form-item   :label-col="{ span: 7 }" :wrapper-col="{ span: 8 }"  v-if="this.shortCode!=='' && this.shortCode!== undefined && this.shortCode!== null " label="简码" prop="short" has-feedback>
        <a-input @keyup="shortChange($event)" v-decorator="['short',validatorRules.short]" v-model="shortInput"></a-input>
      </a-form-item>
    </a-form>
<!--      <input style="margin-top: 20px;margin-left:39%;border: 1px solid rgb(119, 119, 119);    height: 30px;-->
<!--        width: 220px;" id="testInput" type="text" placeholder="请输入" @keyup="textchange($event)" v-model="inputVal">-->
<!--       <span style="margin-left:10px;"><a-icon type="close-circle" :style="{ fontSize: '23px' }"v-if="errorStatus"/>-->
<!--        <a-icon type="check-circle" :style="{ fontSize: '23px' }" theme="twoTone" two-tone-color="#52c41a" v-if="successStatus"/></span>-->
        <div style="margin-left: 39%;margin-top: 20px"> <a-button type="primary" @click="submit" >提交</a-button>
        <a-button style="margin-left: 10%" type="primary" @click="feedback" > 反馈</a-button>
        </div>
    <feeback-form ref="modalForm" @ok="modalFormOk" :wordInfo="wordInfo"
                  @feebackResult="feedbackResult()"></feeback-form>
    <div style="width: 100%">
      <img src="../../assets/sketchMap.png">
    </div>
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
    mixins: [JeecgListMixin, mixinDevice],
    components: {
      FeebackForm
    },
    data() {

      this.loadData = debounce(this.loadData, 800)//消抖
      return {
        form: this.$form.createForm(this),
        description: '汉字对照表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: 'center',
            customRender: function(t, r, index) {
              return parseInt(index) + 1
            }
          }
        ],
        validatorRules: {
          full: { rules: [{ validator: this.validateFull }] },
          short:{ rules: [{ validator: this.validateShort}] }
        },
        dictOptions: {},
        superFieldList: [],
        inputVal: '',
        fullStatus: '',
        fullCode: '',
        shortCode: '',
        char: '',
        fullInput:'',
        shortInput:'',
        englishFullInput: '',
        englishShortInput: '',
        chineseFullInput: '',
        chineseShortInput: '',
        wordInfo: '',
        errorStatus: false,
        successStatus: false,
        submitStatus: true,
        allCount:0,
        lossCount:0,

      }
    },
    created() {
      $(function() {
        $('#testId').val('')
        $('#result').val('wrong')
        $('#charResult').val('wrong')
      })
    },
    computed: {
      // importExcelUrl: function(){
      //   return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      // },
    },
    methods: {
      init() {

      },
      loadData() {
        getAction(`/words/znUserWords/queryWord`).then(res => {
          if (res.success) {
            this.fullCode = res.result.word.keyboardSequence
            this.shortCode = res.result.word.keyboardSimpleSequence1
            this.char = res.result.word.words
            this.wordInfo = res.result
          } else {
            this.$message.warning(res.message)
          }
        })
        getAction(`/words/znUserWords/queryCount`).then(result =>{
          if(result.success){
            this.allCount = result.result.allCount;
            this.lossCount = result.result.lossCount;
          }

        })



      },

      fullChange(e) {
        console.log(e)
        console.log(e.target.value)
        var temp = e.target.value
        var pattern = new RegExp('[\u4E00-\u9FA5]+')
        if (pattern.test(temp)) {
          this.chineseFullInput = temp
        } else {
          this.englishFullInput = temp
        }
        console.log(this.englishFullInput)
        console.log(this.chineseFullInput)
        // var codeArray = this.code.split(',')
        //
        // if (codeArray.indexOf(this.englishFullInput) != -1 && this.chineseFullInput === this.char) {
        //   $('#result').val('right')
        //   console.log('ok!')
        //   this.errorStatus = false
        //   this.successStatus = true
        //   this.submitStatus = false
        // } else {
        //   this.errorStatus = true
        //   this.successStatus = false
        //   this.submitStatus = true
        // }

      },
      shortChange(e) {

        var temp = e.target.value
        var pattern = new RegExp('[\u4E00-\u9FA5]+')
        if (pattern.test(temp)) {
          this.chineseShortInput = temp
        } else {
          this.englishShortInput = temp
        }
        console.log(this.englishShortInput)
        console.log(this.chineseShortInput)
        // var codeArray = this.code.split(',')
        // if (codeArray.indexOf(this.englishShortInput) != -1 && this.chineseShortInput === this.char) {
        //   $('#result').val('right')
        //   console.log('ok!')
        //   this.errorStatus = false
        //   this.successStatus = true
        //   this.submitStatus = false
        // } else {
        //   this.errorStatus = true
        //   this.successStatus = false
        //   this.submitStatus = true
        // }

      },
      submit() {
        console.log(this.shortCode)
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            var userWord = that.wordInfo.userWord
            userWord.ifChecked = 1
            userWord.ifPassed = 1
            userWord.input = that.englishFullInput
            userWord.inputJ = that.englishShortInput
            putAction(`/words/znUserWords/edit`, userWord).then(res => {
              if (res.success) {
                that.form = that.$form.createForm(that);
                that.loadData()
                that.inputVal = ''
                that.fullStatus = ''
                that.fullCode = ''
                that.shortCode = ''
                that.char = ''
                that.fullInput = ''
                that.shortInput = ''
                that.englishFullInput = ''
                that.englishShortInput = ''
                that.chineseFullInput = ''
                that.chineseShortInput = ''
                that.wordInfo = ''
                that.errorStatus = false
                that.successStatus = false
                that.submitStatus = true
              }
            })
          }
        })
      },
      feedback(record) {
        // record.formValue.
        console.log('Clicked cancel button')
        // this.visible = true;
        this.$refs['modalForm'].show(record)
      },

      feedbackResult() {
        this.form = this.$form.createForm(this);
        this.loadData()
        this.inputVal = ''
        this.fullStatus = ''
        this.fullCode = ''
        this.shortCode = ''
        this.char = ''
        this.fullInput = ''
        this.shortInput = ''
        this.englishFullInput = ''
        this.englishShortInput = ''
        this.chineseFullInput = ''
        this.chineseShortInput = ''
        this.wordInfo = ''
        this.errorStatus = false
        this.successStatus = false
        this.submitStatus = true
      },
      validateFull(rule, value, callback) {
        if (value === '') {
          callback(new Error('请输入全码'))
        } else {
          var codeArray = this.fullCode.split(',')
          console.log(value)
          console.log(codeArray.indexOf(this.englishFullInput) != -1 )
          console.log(this.chineseFullInput)
          console.log(this.char)
          if (codeArray.indexOf(this.englishFullInput) != -1 && value === this.char) {
            callback()
          }else{
            callback(new Error('输入与全码不匹配'));
            this.$refs.ruleForm.validateField('checkPass')
          }

        }
      },
      validateShort(rule, value, callback) {
        console.log(this.shortCode)
        if (this.shortCode != undefined && this.shortCode != null && this.shortCode != '') {
          console.log(value)
          if (value === '') {
            callback(new Error('请输入简码'))
          } else {
            var codeArray = this.shortCode.split(',')
            console.log(codeArray)
            if (codeArray.indexOf(this.englishShortInput) != -1 && value === this.char) {
              callback()
            }else{
              callback(new Error('输入与简码不匹配'));
              this.$refs.ruleForm.validateField('checkPass')
            }
          }
        }else{
          callback()
        }
      }

    }

  }

</script>
