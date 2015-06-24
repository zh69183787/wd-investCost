/**
 * Created by Administrator on 2015/3/2.
 */
var contractAttributeData = [ {
	"id" : "A",
	"dictNo" : "A",
	"dictOrder" : 1,
	"name" : "招标类",
	"isParent" : true,
	"children" : [ {
		"id" : "A1",
		"dictNo" : "A1",
		"dictOrder" : 1,
		"name" : "勘察设计类",
		"isParent" : true,
		"children" : [ {
			"id" : "A11",
			"dictNo" : "A11",
			"dictOrder" : 1,
			"isParent" : true,
			"name" : "设计",
			"children" : [ {
				"id" : "A111",
				"dictNo" : "A111",
				"dictOrder" : 1,
				"isParent" : true,
				"name" : "总体总包全线设计",
				"children" : [ {
					"id" : "A1111",
					"dictNo" : "A1111",
					"dictOrder" : 1,
					"name" : "总体总包及全部设计项目"
				} ]
			}, {
				"id" : "A112",
				"dictNo" : "A112",
				"dictOrder" : 2,
				"isParent" : true,
				"name" : "总体总包及部分分项设计",
				"children" : [ {
					"id" : "A1121",
					"dictNo" : "A1121",
					"dictOrder" : 1,
					"name" : "总体总包及部分分项设计"
				}, {
					"id" : "A1122",
					"dictNo" : "A1122",
					"dictOrder" : 2,
					"name" : "分项设计（土建）"
				}, {
					"id" : "A1123",
					"dictNo" : "A1123",
					"dictOrder" : 3,
					"name" : "分项设计（机电）"
				} ]
			} ]
		}, {
			"id" : "A12",
			"dictNo" : "A12",
			"dictOrder" : 2,
			"isParent" : true,
			"name" : "勘察",
			"children" : [ {
				"id" : "A121",
				"dictNo" : "A121",
				"dictOrder" : 1,
				"isParent" : true,
				"name" : "车站及区间详勘",
				"children" : [ {
					"id" : "A1211",
					"dictNo" : "A1211",
					"dictOrder" : 1,
					"name" : "车站及区间"
				},{
					"id" : "A1212",
					"dictNo" : "A1212",
					"dictOrder" : 2,
					"name" : "车站"
				},{
					"id" : "A1213",
					"dictNo" : "A1213",
					"dictOrder" : 3,
					"name" : "区间"
				}]
			}, {
				"id" : "A123",
				"dictNo" : "A123",
				"dictOrder" : 2,
				"isParent" : true,
				"name" : "车站详勘",
				"children" : [ {
					"id" : "A1231",
					"dictNo" : "A1231",
					"dictOrder" : 1,
					"name" : "水文地质勘察"
				} ]
			}, {
				"id" : "A122",
				"dictNo" : "A122",
				"dictOrder" : 3,
				"isParent" : true,
				"name" : "停车场（车辆段）详勘",
				"children" : [ {
					"id" : "A1221",
					"dictNo" : "A1221",
					"dictOrder" : 1,
					"name" : "段场"
				} ]
			} ]
		} ]
	}, {
		"id" : "A2",
		"dictNo" : "A2",
		"dictOrder" : 2,
		"name" : "施工类",
		"isParent" : true,
		"children" : [ {
			"id" : "A21",
			"dictNo" : "A21",
			"dictOrder" : 1,
			"isParent" : true,
			"name" : "土建",
			"children" : [ {
				"id" : "A211",
				"dictNo" : "A211",
				"dictOrder" : 1,
				"isParent" : true,
				"name" : "车站及区间",
				"children" : [ {
					"id" : "A2111",
					"dictNo" : "A2111",
					"dictOrder" : 1,
					"name" : "车站"
				}, {
					"id" : "A2112",
					"dictNo" : "A2112",
					"dictOrder" : 2,
					"name" : "区间"
				}, {
					"id" : "A2113",
					"dictNo" : "A2113",
					"dictOrder" : 3,
					"name" : "车站及区间"
				} ]
			}, {
				"id" : "A212",
				"dictNo" : "A212",
				"dictOrder" : 2,
				"isParent" : true,
				"name" : "车站装修",
				"children" : [ {
					"id" : "A2121",
					"dictNo" : "A2121",
					"dictOrder" : 1,
					"name" : "车站装修"
				}, {
					"id" : "A2122",
					"dictNo" : "A2122",
					"dictOrder" : 2,
					"name" : "车站装修及安装"
				} ]
			}, {
				"id" : "A213",
				"dictNo" : "A213",
				"dictOrder" : 3,
				"isParent" : true,
				"name" : "停车场",
				"children" : [ {
					"id" : "A2131",
					"dictNo" : "A2131",
					"dictOrder" : 1,
					"name" : "房建"
				}, {
					"id" : "A2132",
					"dictNo" : "A2132",
					"dictOrder" : 2,
					"name" : "市政"
				}, {
					"id" : "A2133",
					"dictNo" : "A2133",
					"dictOrder" : 3,
					"name" : "绿化"
				}, {
					"id" : "A2134",
					"dictNo" : "A2134",
					"dictOrder" : 4,
					"name" : "房建及市政"
				}

				]
			}, {
				"id" : "A214",
				"dictNo" : "A214",
				"dictOrder" : 4,
				"name" : "主变电所土建(不含电力外线）"
			}, {
				"id" : "A215",
				"dictNo" : "A215",
				"dictOrder" : 5,
				"name" : "轨道"
			}, {
				"id" : "A216",
				"dictNo" : "A216",
				"dictOrder" : 6,
				"name" : "导向标志"
			}, {
				"id" : "A217",
				"dictNo" : "A217",
				"dictOrder" : 7,
				"name" : "声屏障"
			}, {
				"id" : "A218",
				"dictNo" : "A218",
				"dictOrder" : 8,
				"name" : "道路"
			}, {
				"id" : "A219",
				"dictNo" : "A219",
				"dictOrder" : 9,
				"name" : "桥梁"
			}, {
				"id" : "A21A",
				"dictNo" : "A21A",
				"dictOrder" : 10,
				"name" : "区间旁通道"
			}, {
				"id" : "A21B",
				"dictNo" : "A21B",
				"dictOrder" : 11,
				"name" : "监测",
				"isParent" : true,
				"children" : [ {
					"id" : "A21B1",
					"dictNo" : "A21B1",
					"dictOrder" : 1,
					"name" : "环境监测"
				}, {
					"id" : "A21B2",
					"dictNo" : "A21B2",
					"dictOrder" : 2,
					"name" : "轴线复测"
				}, {
					"id" : "A21B3",
					"dictNo" : "A21B3",
					"dictOrder" : 3,
					"name" : "材料检测"
				}, {
					"id" : "A21B4",
					"dictNo" : "A21B4",
					"dictOrder" : 4,
					"name" : "桩基检测"
				}, {
					"id" : "A21B5",
					"dictNo" : "A21B5",
					"dictOrder" : 5,
					"name" : "钢轨探伤"
				}, {
					"id" : "A21B6",
					"dictNo" : "A21B6",
					"dictOrder" : 6,
					"name" : "后期沉降监测"
				} ]
			}, {
				"id" : "A21C",
				"dictNo" : "A21C",
				"dictOrder" : 12,
				"name" : "预制构件",
				"isParent" : true,
				"children" : [ {
					"id" : "A21C1",
					"dictNo" : "A21C1",
					"dictOrder" : 1,
					"name" : "梁制作"
				}, {
					"id" : "A21C2",
					"dictNo" : "A21C2",
					"dictOrder" : 2,
					"name" : "管片"
				}, {
					"id" : "A21C3",
					"dictNo" : "A21C3",
					"dictOrder" : 3,
					"name" : "预制轨枕"
				} ]
			}, {
				"id" : "A21D",
				"dictNo" : "A21D",
				"dictOrder" : 13,
				"name" : "出入口顶盖"
			}, {
				"id" : "A21F",
				"dictNo" : "A31F",
				"dictOrder" : 114,
				"name" : "水系调整"
			}, {
				"id" : "A21E",
				"dictNo" : "A21E",
				"dictOrder" : 15,
				"name" : "其他"
			} ]
		}, {
			"id" : "A22",
			"dictNo" : "A22",
			"dictOrder" : 2,
			"isParent" : true,
			"name" : "机电",
			"children" : [ {
				"id" : "A221",
				"dictNo" : "A221",
				"dictOrder" : 1,
				"name" : "车站风水电设备"
			}, {
				"id" : "A222",
				"dictNo" : "A222",
				"dictOrder" : 2,
				"name" : "主变电所设备"
			}, {
				"id" : "A223",
				"dictNo" : "A223",
				"dictOrder" : 3,
				"name" : "通信（含PIS、传输系统、CCTV)"
			}, {
				"id" : "A224",
				"dictNo" : "A224",
				"dictOrder" : 4,
				"name" : "无线通信（直放站、漏缆、手持台）"
			}, {
				"id" : "A225",
				"dictNo" : "A225",
				"dictOrder" : 5,
				"name" : "信号"
			}, {
				"id" : "A226",
				"dictNo" : "A226",
				"dictOrder" : 6,
				"name" : "防灾报警/设备监控/门警系统"
			}, {
				"id" : "A227",
				"dictNo" : "A227",
				"dictOrder" : 7,
				"name" : "气体灭火"
			}, {
				"id" : "A228",
				"dictNo" : "A228",
				"dictOrder" : 8,
				"name" : "接触网/干线电缆/防迷流"
			}, {
				"id" : "A229",
				"dictNo" : "A229",
				"dictOrder" : 9,
				"name" : "自动售检票设备"
			}, {
				"id" : "A22A",
				"dictNo" : "A22A",
				"dictOrder" : 10,
				"name" : "牵引/降压变电所"
			}, {
				"id" : "A22B",
				"dictNo" : "A22B",
				"dictOrder" : 11,
				"name" : "屏蔽门（安全门）"
			}, {
				"id" : "A22C",
				"dictNo" : "A22C",
				"dictOrder" : 12,
				"name" : "停车场(车辆段)工艺设备"
			}, {
				"id" : "A22D",
				"dictNo" : "A22D",
				"dictOrder" : 13,
				"name" : "防灾报警/设备监控/门警系统/气体灭火"
			}, {
				"id" : "A22E",
				"dictNo" : "A22E",
				"dictOrder" : 14,
				"name" : "接触网/干线电缆/防迷流/牵引/降压变电所"
			}, {
				"id" : "A22F",
				"dictNo" : "A22F",
				"dictOrder" : 15,
				"name" : "其他"
			}]
		} ]
	}, {
		"id" : "A3",
		"dictNo" : "A3",
		"dictOrder" : 3,
		"name" : "监理类",
		"isParent" : true,
		"children" : [ {
			"id" : "A31",
			"dictNo" : "A31",
			"dictOrder" : 1,
			"name" : "土建",
			"isParent" : true,
			"children" : [ {
				"id" : "A311",
				"dictNo" : "A311",
				"dictOrder" : 1,
				"name" : "车站及区间（含地下区间旁通道）"
			}, {
				"id" : "A312",
				"dictNo" : "A312",
				"dictOrder" : 2,
				"name" : "停车场（市政、房建、绿化）"
			}, {
				"id" : "A313",
				"dictNo" : "A313",
				"dictOrder" : 3,
				"name" : "主变电所"
			}, {
				"id" : "A314",
				"dictNo" : "A314",
				"dictOrder" : 4,
				"name" : "轨道"
			}, {
				"id" : "A315",
				"dictNo" : "A315",
				"dictOrder" : 5,
				"name" : "声屏障"
			}, {
				"id" : "A316",
				"dictNo" : "A316",
				"dictOrder" : 6,
				"name" : "道路"
			}, {
				"id" : "A317",
				"dictNo" : "A317",
				"dictOrder" : 7,
				"name" : "桥梁"
			}, {
				"id" : "A318",
				"dictNo" : "A318",
				"dictOrder" : 8,
				"name" : "人防门/防火门"
			}, {
				"id" : "A319",
				"dictNo" : "A319",
				"dictOrder" : 9,
				"name" : "预制构件",
				"isParent" : true,
				"children" : [ {
					"id" : "A3191",
					"dictNo" : "A3191",
					"dictOrder" : 1,
					"name" : "梁制作"
				}, {
					"id" : "A3192",
					"dictNo" : "A3192",
					"dictOrder" : 2,
					"name" : "管片"
				} ]
			}, {
				"id" : "A31B",
				"dictNo" : "A31B",
				"dictOrder" : 10,
				"name" : "水系调整"
			}, {
				"id" : "A31A",
				"dictNo" : "A31A",
				"dictOrder" : 11,
				"name" : "其他"
			} ]
		}, {
			"id" : "A32",
			"dictNo" : "A32",
			"dictOrder" : 2,
			"name" : "机电",
			"isParent" : true,
			"children" : [ {
				"id" : "A321",
				"dictNo" : "A321",
				"dictOrder" : 1,
				"name" : "车站装修、风水电设备安装"
			}, {
				"id" : "A322",
				"dictNo" : "A322",
				"dictOrder" : 2,
				"name" : "通信（含PIS、传输系统、CCTV)，含无线"
			}, {
				"id" : "A323",
				"dictNo" : "A323",
				"dictOrder" : 3,
				"name" : "信号"
			}, {
				"id" : "A324",
				"dictNo" : "A324",
				"dictOrder" : 4,
				"name" : "气体灭火/防灾报警/设备监控/门警系统"
			}, {
				"id" : "A325",
				"dictNo" : "A325",
				"dictOrder" : 5,
				"name" : "接触网/干线电缆/防迷流"
			}, {
				"id" : "A326",
				"dictNo" : "A326",
				"dictOrder" : 6,
				"name" : "自动售检票设备"
			}, {
				"id" : "A327",
				"dictNo" : "A327",
				"dictOrder" : 7,
				"name" : "屏蔽门/安全门/自动扶梯/垂直电梯"
			}, {
				"id" : "A328",
				"dictNo" : "A328",
				"dictOrder" : 8,
				"name" : "牵引/降压变电所"
			}, {
				"id" : "A329",
				"dictNo" : "A329",
				"dictOrder" : 9,
				"name" : "停车场/车辆段工艺设备"
			}, {
				"id" : "A32A",
				"dictNo" : "A32A",
				"dictOrder" : 10,
				"name" : "防灾报警/设备监控/门警系统/气体灭火"
			}, {
				"id" : "A32B",
				"dictNo" : "A32B",
				"dictOrder" : 11,
				"name" : "接触网/干线电缆/防迷流/牵引/降压变电所"
			}, {
				"id" : "A32C",
				"dictNo" : "A32C",
				"dictOrder" : 12,
				"name" : "停车场/车辆段工艺设备/屏蔽门/安全门/自动扶梯/垂直电梯"
			}, {
				"id" : "A32D",
				"dictNo" : "A32D",
				"dictOrder" : 13,
				"name" : "其他"
			}

			]
		} ]
	}, {
		"id" : "A4",
		"dictNo" : "A4",
		"dictOrder" : 4,
		"name" : "采购类",
		"isParent" : true,
		"children" : [{
			"id" : "A41",
			"dictNo" : "A41",
			"dictOrder" : 1,
			"name" : "土建",
			"isParent" : true,
			"children" : [ {
				"id" : "A411",
				"dictNo" : "A411",
				"dictOrder" : 1,
				"name" : "轨道",
				"isParent" : true,
				"children" : [ {
					"id" : "A4111",
					"dictNo" : "A4111",
					"dictOrder" : 1,
					"name" : "扣件"
				} ]
			}, {
				"id" : "A412",
				"dictNo" : "A412",
				"dictOrder" : 2,
				"name" : "装饰材料",
				"isParent" : true,
				"children" : [ {
					"id" : "A4121",
					"dictNo" : "A4121",
					"dictOrder" : 1,
					"name" : "顶部材料"
				}, {
					"id" : "A4122",
					"dictNo" : "A4122",
					"dictOrder" : 2,
					"name" : "墙面材料"
				}, {
					"id" : "A4123",
					"dictNo" : "A4123",
					"dictOrder" : 3,
					"name" : "地面材料"
				}, {
					"id" : "A4124",
					"dictNo" : "A4124",
					"dictOrder" : 4,
					"name" : "灯具"
				}, {
					"id" : "A4125",
					"dictNo" : "A4125",
					"dictOrder" : 5,
					"name" : "防火门"
				}, {
					"id" : "A4126",
					"dictNo" : "A4126",
					"dictOrder" : 6,
					"name" : "人防门"
				}, {
					"id" : "A4127",
					"dictNo" : "A4127",
					"dictOrder" : 7,
					"name" : "客服中心"
				}, {
					"id" : "A4128",
					"dictNo" : "A4128",
					"dictOrder" : 8,
					"name" : "不锈钢扶栏等"
				}]
			}, {
				"id" : "A413",
				"dictNo" : "A413",
				"dictOrder" : 3,
				"name" : "辅助设施",
				"isParent" : true,
				"children" : [ {
					"id" : "A4131",
					"dictNo" : "A4131",
					"dictOrder" : 1,
					"name" : "座椅、垃圾箱"
				} ]
			} ]
		}, {
			"id" : "A42",
			"dictNo" : "A42",
			"dictOrder" : 2,
			"name" : "机电",
			"isParent" : true,
			"children" : [ {
				"id" : "A421",
				"dictNo" : "A421",
				"dictOrder" : 1,
				"name" : "车辆"
			}, {
				"id" : "A422",
				"dictNo" : "A422",
				"dictOrder" : 2,
				"name" : "信号"
			}, {
				"id" : "A423",
				"dictNo" : "A423",
				"dictOrder" : 3,
				"name" : "停车场工艺设备"
			}, {
				"id" : "A424",
				"dictNo" : "A424",
				"dictOrder" : 4,
				"name" : "主变电所",
				"isParent" : true,
				"children" : [ {
					"id" : "A4241",
					"dictNo" : "A4241",
					"dictOrder" : 1,
					"name" : "110KV GIS(台/间隔)"
				}, {
					"id" : "A4242",
					"dictNo" : "A4242",
					"dictOrder" : 2,
					"name" : "110KV/35KV 变压器"
				}, {
					"id" : "A4243",
					"dictNo" : "A4243",
					"dictOrder" : 3,
					"name" : "35KV GIS"
				} ]
			}, {
				"id" : "A425",
				"dictNo" : "A425",
				"dictOrder" : 5,
				"name" : "牵引降压变电所",
				"isParent" : true,
				"children" : [ {
					"id" : "A4251",
					"dictNo" : "A4251",
					"dictOrder" : 1,
					"name" : "35KV GIS开关"
				}, {
					"id" : "A4252",
					"dictNo" : "A4252",
					"dictOrder" : 2,
					"name" : "1500V 直流开关"
				}, {
					"id" : "A4253",
					"dictNo" : "A4253",
					"dictOrder" : 3,
					"name" : "1500V 整流变压器"
				}, {
					"id" : "A4254",
					"dictNo" : "A4254",
					"dictOrder" : 4,
					"name" : "400V 开关柜"
				}, {
					"id" : "A4255",
					"dictNo" : "A4255",
					"dictOrder" : 5,
					"name" : "35KV/400V 动力变压器"
				}, {
					"id" : "A4256",
					"dictNo" : "A4256",
					"dictOrder" : 6,
					"name" : "400V有缘滤波及无功补偿装置"
				}, {
					"id" : "A4257",
					"dictNo" : "A4257",
					"dictOrder" : 7,
					"name" : "UPS装置"
				} ]
			}, {
				"id" : "A426",
				"dictNo" : "A426",
				"dictOrder" : 6,
				"name" : "环控",
				"isParent" : true,
				"children" : [ {
					"id" : "A4261",
					"dictNo" : "A4261",
					"dictOrder" : 1,
					"name" : "单向轴流风机"
				}, {
					"id" : "A4262",
					"dictNo" : "A4262",
					"dictOrder" : 2,
					"name" : "可逆轴流风机"
				}, {
					"id" : "A4266",
					"dictNo" : "A4266",
					"dictOrder" : 3,
					"name" : "单向轴流风机及可逆轴流风机"
				}, {
					"id" : "A4263",
					"dictNo" : "A4263",
					"dictOrder" : 4,
					"name" : "组合式空调箱"
				}, {
					"id" : "A4264",
					"dictNo" : "A4264",
					"dictOrder" : 5,
					"name" : "冷水机组"
				}, {
					"id" : "A4265",
					"dictNo" : "A4265",
					"dictOrder" : 6,
					"name" : "冷却塔"
				}, {
					"id" : "A4267",
					"dictNo" : "A4267",
					"dictOrder" : 7,
					"name" : "复合风管"
				}, {
					"id" : "A4268",
					"dictNo" : "A4268",
					"dictOrder" : 8,
					"name" : "综合支吊架"
				} ]
			}, {
				"id" : "A427",
				"dictNo" : "A427",
				"dictOrder" : 7,
				"name" : "动力照明",
				"isParent" : true,
				"children" : [ {
					"id" : "A4271",
					"dictNo" : "A4271",
					"dictOrder" : 1,
					"name" : "环控电控柜"
				}, {
					"id" : "A4272",
					"dictNo" : "A4272",
					"dictOrder" : 2,
					"name" : "部分动力柜"
				}, {
					"id" : "A4273",
					"dictNo" : "A4273",
					"dictOrder" : 3,
					"name" : "变频柜"
				} ]
			}, {
				"id" : "A428",
				"dictNo" : "A428",
				"dictOrder" : 8,
				"name" : "自动售检票",
				"isParent" : true,
				"children" : [ {
					"id" : "A4281",
					"dictNo" : "A4281",
					"dictOrder" : 1,
					"name" : "售票机/检票机"
				}, {
					"id" : "A4282",
					"dictNo" : "A4282",
					"dictOrder" : 2,
					"name" : "票卡"
				} ]
			}, {
				"id" : "A429",
				"dictNo" : "A429",
				"dictOrder" : 9,
				"name" : "电梯",
				"isParent" : true,
				"children" : [ {
					"id" : "A4291",
					"dictNo" : "A4291",
					"dictOrder" : 1,
					"name" : "自动扶梯"
				}, {
					"id" : "A4292",
					"dictNo" : "A4292",
					"dictOrder" : 2,
					"name" : "垂直电梯"
				}, {
					"id" : "A4293",
					"dictNo" : "A4293",
					"dictOrder" : 3,
					"name" : "自动扶梯及垂直电梯"
				} ]
			} ]
		} ]
	}]
}, {
	"id" : "B",
	"dictNo" : "B",
	"dictOrder" : 2,
	"name" : "非招标类",
	"isParent" : true,
	"children" : [ {
		"id" : "B1",
		"dictNo" : "B1",
		"dictOrder" : 1,
		"name" : "前期管线类",
		"isParent" : true,
		"children" : [ {
			"id" : "B11",
			"dictNo" : "B11",
			"dictOrder" : 1,
			"name" : "上水"
		}, {
			"id" : "B12",
			"dictNo" : "B12",
			"dictOrder" : 2,
			"name" : "燃气"
		}, {
			"id" : "B13",
			"dictNo" : "B13",
			"dictOrder" : 3,
			"name" : "临水临电"
		}, {
			"id" : "B14",
			"dictNo" : "B14",
			"dictOrder" : 4,
			"name" : "非电信"
		}, {
			"id" : "B15",
			"dictNo" : "B15",
			"dictOrder" : 5,
			"name" : "电信"
		}, {
			"id" : "B16",
			"dictNo" : "B16",
			"dictOrder" : 6,
			"name" : "电力"
		}, {
			"id" : "B17",
			"dictNo" : "B17",
			"dictOrder" : 7,
			"name" : "交通"
		}, {
			"id" : "B18",
			"dictNo" : "B18",
			"dictOrder" : 8,
			"name" : "市政"
		}, {
			"id" : "B19",
			"dictNo" : "B19",
			"dictOrder" : 9,
			"name" : "专用管线"
		} , {
			"id" : "B1A",
			"dictNo" : "B1A",
			"dictOrder" : 10,
			"name" : "其他"
		}]
	}, {
		"id" : "B2",
		"dictNo" : "B2",
		"dictOrder" : 2,
		"name" : "咨询服务类",
		"isParent" : true,
		"children" : [ {
			"id" : "B21",
			"dictNo" : "B21",
			"dictOrder" : 1,
			"name" : "预工可"
		}, {
			"id" : "B22",
			"dictNo" : "B22",
			"dictOrder" : 2,
			"name" : "初勘"
		}, {
			"id" : "B23",
			"dictNo" : "B23",
			"dictOrder" : 3,
			"name" : "物探"
		}, {
			"id" : "B24",
			"dictNo" : "B24",
			"dictOrder" : 4,
			"name" : "灾评"
		}, {
			"id" : "B25",
			"dictNo" : "B25",
			"dictOrder" : 5,
			"name" : "稳评"
		}, {
			"id" : "B26",
			"dictNo" : "B26",
			"dictOrder" : 6,
			"name" : "客流预测"
		}, {
			"id" : "B27",
			"dictNo" : "B27",
			"dictOrder" : 7,
			"name" : "节能"
		}, {
			"id" : "B28",
			"dictNo" : "B28",
			"dictOrder" : 8,
			"name" : "环评"
		}, {
			"id" : "B29",
			"dictNo" : "B29",
			"dictOrder" : 9,
			"name" : "风评"
		}, {
			"id" : "B2A",
			"dictNo" : "B2A",
			"dictOrder" : 10,
			"name" : "防震"
		}, {
			"id" : "B2B",
			"dictNo" : "B2B",
			"dictOrder" : 11,
			"name" : "防雷"
		}, {
			"id" : "B2C",
			"dictNo" : "B2C",
			"dictOrder" : 12,
			"name" : "评审"
		}, {
			"id" : "B2D",
			"dictNo" : "B2D",
			"dictOrder" : 13,
			"name" : "卫生"
		}, {
			"id" : "B2E",
			"dictNo" : "B2E",
			"dictOrder" : 14,
			"name" : "安评"
		}, {
			"id" : "B2F",
			"dictNo" : "B2F",
			"dictOrder" : 15,
			"name" : "调研"
		}, {
			"id" : "B2G",
			"dictNo" : "B2G",
			"dictOrder" : 16,
			"name" : "水务"
		}, {
			"id" : "B2H",
			"dictNo" : "B2H",
			"dictOrder" : 17,
			"name" : "设计咨询"
		}, {
			"id" : "B2I",
			"dictNo" : "B2I",
			"dictOrder" : 18,
			"name" : "审图"
		}, {
			"id" : "B2J",
			"dictNo" : "B2J",
			"dictOrder" : 19,
			"name" : "交通"
		}, {
			"id" : "B2K",
			"dictNo" : "B2K",
			"dictOrder" : 20,
			"name" : "管线综合"
		}, {
			"id" : "B2L",
			"dictNo" : "B2L",
			"dictOrder" : 21,
			"name" : "档案"
		}, {
			"id" : "B2M",
			"dictNo" : "B2M",
			"dictOrder" : 22,
			"name" : "勘测定界"
		}, {
			"id" : "B2N",
			"dictNo" : "B2N",
			"dictOrder" : 23,
			"name" : "测量"
		}, {
			"id" : "B2O",
			"dictNo" : "B2O",
			"dictOrder" : 24,
			"name" : "施工图预算"
		}, {
			"id" : "B2P",
			"dictNo" : "B2P",
			"dictOrder" : 26,
			"name" : "消防"
		}, {
			"id" : "B2Q",
			"dictNo" : "B2Q",
			"dictOrder" : 27,
			"name" : "检测"
		}, {
			"id" : "B2R",
			"dictNo" : "B2R",
			"dictOrder" : 28,
			"name" : "旁通道"
		}, {
			"id" : "B2S",
			"dictNo" : "B2S",
			"dictOrder" : 29,
			"name" : "监测"
		}, {
			"id" : "B2T",
			"dictNo" : "B2T",
			"dictOrder" : 30,
			"name" : "保安"
		}, {
			"id" : "B2U",
			"dictNo" : "B2U",
			"dictOrder" : 31,
			"name" : "前期办证"
		}, {
			"id" : "B2V",
			"dictNo" : "B2V",
			"dictOrder" : 32,
			"name" : "协管"
		}, {
			"id" : "B2W",
			"dictNo" : "B2W",
			"dictOrder" : 33,
			"name" : "保洁"
		}, {
			"id" : "B2X",
			"dictNo" : "B2X",
			"dictOrder" : 34,
			"name" : "其他"
		} ]
	}, {
		"id" : "B3",
		"dictNo" : "B3",
		"dictOrder" : 3,
		"name" : "科研",
		"isParent" : true,
		"children" : [ {
			"id" : "B31",
			"dictNo" : "B31",
			"dictOrder" : 1,
			"name" : "科研"
		} ]
	}, {
		"id" : "B4",
		"dictNo" : "B4",
		"dictOrder" : 4,
		"name" : "特殊批准类",
		"isParent" : true,
		"children" : [ {
			"id" : "B41",
			"dictNo" : "B41",
			"dictOrder" : 1,
			"name" : "投资分工"
		}, {
			"id" : "B42",
			"dictNo" : "B42",
			"dictOrder" : 2,
			"name" : "动拆迁"
		}, {
			"id" : "B43",
			"dictNo" : "B43",
			"dictOrder" : 3,
			"name" : "合作"
		}, {
			"id" : "B44",
			"dictNo" : "B44",
			"dictOrder" : 4,
			"name" : "审价"
		}, {
			"id" : "B45",
			"dictNo" : "B45",
			"dictOrder" : 5,
			"name" : "审计"
		}, {
			"id" : "B46",
			"dictNo" : "B46",
			"dictOrder" : 6,
			"name" : "保险"
		}, {
			"id" : "B47",
			"dictNo" : "B47",
			"dictOrder" : 7,
			"name" : "借地"
		}, {
			"id" : "B48",
			"dictNo" : "B48",
			"dictOrder" : 8,
			"name" : "绿化"
		}, {
			"id" : "B49",
			"dictNo" : "B49",
			"dictOrder" : 9,
			"name" : "招标代理"
		}, {
			"id" : "B4A",
			"dictNo" : "B4A",
			"dictOrder" : 10,
			"name" : "投资监理"
		}, {
			"id" : "B4B",
			"dictNo" : "B4B",
			"dictOrder" : 2,
			"name" : "代建"
		}, {
			"id" : "B4C",
			"dictNo" : "B4C",
			"dictOrder" : 2,
			"name" : "维稳"
		}, {
			"id" : "B4D",
			"dictNo" : "B4D",
			"dictOrder" : 2,
			"name" : "运营管理"
		}, {
			"id" : "B4E",
			"dictNo" : "B4E",
			"dictOrder" : 2,
			"name" : "其他"
		} ]
	} ]
}

];
