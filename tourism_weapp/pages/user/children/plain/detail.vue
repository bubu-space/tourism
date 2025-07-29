<template>
	<view>
		<view class="title">
			<view class="info">
				<view class="text-xxl text-jb">我的行程规划</view>
				<view class="text-df text-gray margin-top-sm">有规划，更安心</view>
			</view>
		</view>
		
		<view style="padding: 14px;">
			<view class="form_group">
				<view class="text-xl">规划名称</view>
				<view class="form_item flex align-center">
					<input type="text" v-model="formData.title" placeholder="规划名称" />
				</view>
			</view>
		</view>
		
		<view class="form padding">
			
			<view v-for="(item,index) in formData.content" :key="index" style="margin-bottom: 40px;">
				<view class="text-center text-xl">
					<text>节点{{index+1}}</text>
					<text @click="remove(index)" class=".cuIcon-delete margin-left-sm"></text>
				</view>
				
				<view class="form_group margin-top-xl">
					<view class="text-xl">节点时间</view>
					<view class="margin-top-sm">
						<uni-datetime-picker type="datetime" v-model="item.dateTime" @change="datePickerChange" @show="datePickerShow=true" @maskClick="datePickerShow=false" />
					</view>
				</view>
				
				<view class="form_group">
					<view class="text-xl">节点动作</view>
					<view class="form_item flex align-center">
						<input type="text" v-model="item.describle" placeholder="节点动作" />
					</view>
				</view>
			</view>
		</view>
		
			
		<view class="cu-bar tabbar bg-white foot flex" v-if="!datePickerShow">
			<button @click="append" class="cu-btn line-blue flex-sub margin" style="height: 40px;line-height: 40px;">添加</button>
			<button @click="onSubmit" class="cu-btn bg-orange flex-sub margin" style="height: 40px;line-height: 40px;">保存</button>
		</view>
	</view>
</template>

<script>
	import plainApi from "@/api/plainApi.js"
		
	export default {
		data() {
			return {
				datePickerShow: false,
				formData: {
					title: null,
					content: []
				},
			}
		},
		onLoad(option){
			let id = option.id
			if(id){
				plainApi.detail({id}).then(res => {
					this.formData = res.data
				})
			}
		},
		methods: {
			onSubmit(){
				if(this.formData.id){
					plainApi.edit(this.formData).then(res => {
						uni.showToast({
							title:'修改成功',
							icon:'success'
						})
					})
				}else{
					plainApi.saveplan(this.formData).then(res => {
						uni.showToast({
							title:'保存成功',
							icon:'success'
						})
					})
				}
			},
			append(){
				this.formData.content.push({
					dateTime: null,
					describle: null,
				})
			},
			remove(index){
				this.formData.content.splice(index,1)
			},
			datePickerChange(){
				this.datePickerShow = false
			}
		}
	}
</script>



<style lang="scss">
	.title{
		background-color: #fff;
		padding: 14px;
		
		.info{
			padding: 14px;
			border-radius: 6px;
			background: linear-gradient(to right,#E1FAF6,#E6EBFE);
		}
	}
	
	page{
		background-color: #fff;
	}
	
	.form{
		padding-bottom: 80px;
	}
		
	.form_group{
		margin-bottom: 18px;
		flex-direction: column;
		align-items: flex-start;
		
		.form_item{
			margin-top: 10px;
			border: 1px solid #888;
			border-radius: 2px;
			font-size: 16px;
			padding: 14px;
			display: flex;
			justify-content: space-between;
			
			input{
				flex: 1;
			}
		}
	}
	
	.uni-date-x {
		height: 52px;
	}
	
	.uni-date-x--border {
		border-radius: 2px !important;
		border: 1px solid #888 !important;
	}
	
	.text-jb{
		background: linear-gradient(to right, #63f5f7, #a200ff);
		-webkit-background-clip: text;
		background-clip: text;
		color: transparent;
	}
</style>
