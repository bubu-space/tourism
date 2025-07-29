<template>
	<view>
		<view class="search">
			<view class="inputbox">
				<view class="cuIcon-search"></view>
				<input type="text" @blur="onSearch" v-model:value="spotName" placeholder="搜索标题" />
			</view>
		</view>
		
		<view v-if="dataList.length>0" class="padding-sm">
			<view class="spot-list">
				<view class="spot-item" v-for="(item,index) in dataList" :key="index"  @click="toDetail(item.id)"> 
					<image :src="item.cover.url" style="width: 100%;height: 130px;" />
					<view class="bg-orange cu-tag sm" style="position: absolute;right: 0;top: 0;">{{item.rate}}分</view>
					
					<view class="flex-sub flex flex-direction justify-between padding-sm">
						<view class="h3 title">
							<view class="cu-tag sm bg-red margin-right-sm">{{item.startLevel}}A</view>		
							<text>{{ item.spotName }}</text>
						</view>
						<view class="margin-top-xs">
							<text v-for="a in item.spotTags" class="cu-tag sm bg-blue light">{{a}}</text>
						</view>
						<div class="address weaktext margin-top-xs">{{item.address}}</div>
						<view class="flex justify-between margin-top-xs">
							<text v-if="item.minPrice!==0" class="text-grey"><text class="price text-xl text-red">￥{{item.minPrice}}</text>起</text>
							<text v-else class="text-green">免费</text>
						</view>
					</view>
				</view>
			</view>
		</view>
		
		<empty v-else></empty>
		
		<!-- 添加按钮 -->
		<view style="position: fixed; right: 20px; top: 550px;" @click="scrollToTop">
			<button class="cu-btn cuIcon round bg-green lg">
			  <text class="cuIcon-upblock text-xxl text-bold"></text>
			</button>
		</view>	
		
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"

	export default {
		data() {
			return {
				spotName: null,
				dataList: []
			}
		},
		onLoad() {
			this.getData()
		},
		methods: {
			scrollToTop() {
			  uni.pageScrollTo({
				scrollTop: 0,      // 滚动到顶部
				duration: 300      // 动画时长（ms），可选
			  });
			},
			getData() {
				indexApi.getspotbook({
					spotname: this.spotName
				}).then(res => {
					this.dataList = res.data
				})
			},
			onSearch() {
				this.getData()
			},
			toDetail(id){
				uni.navigateTo({
					url:'/pages/spot/detail?id='+id
				})
			}
		}
	}
</script>

<style lang="scss">
	.spot-list{
		column-count: 2; 
		column-gap: 10px;
		
		.spot-item{
			position: relative;
			break-inside: avoid;
			width: 100%;
			background-color: #fff;
			margin-bottom: 10px;
		}
	}
	
	.search {
		padding: 10px 14px;
		background-color: #fff;

		.inputbox {
			padding: 8px;
			background-color: #F7F7F7;
			display: flex;
			align-items: center;
			gap: 4px;
			border-radius: 4px;
		}

		.nav {
			margin-top: 10px;

			.cu-item {
				border-radius: 2px;
				height: 26px;
				line-height: 26px;
				background-color: #F7F7F7;
				color: #000;
			}

			.cu-item.cur {
				background-color: #FADBD9;
				border-bottom: none;
			}
		}

	}
</style>