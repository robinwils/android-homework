#include "helloworldservice.hh"
#include "utils/Log.h"

namespace android
{
  IMPLEMENT_META_INTERFACE(HelloWorldService, "HelloWorldService");
  /*const String16 IHelloWorldService::descriptor("HelloWorldService");
  const String16& IHelloWorldService::getInterfaceDescriptor() const {
    return IHelloWorldService::descriptor;
  }
  sp<IHelloWorldService> IHelloWorldService::asInterface(const sp<IBinder>& obj) {
    sp<IHelloWorldService> intr;
    if (obj != NULL) {
      intr = static_cast<IHelloWorldService*>(obj->queryLocalInterface(IHelloWorldService::descriptor).get());
      if (intr == NULL) {
	intr = new BpHelloWorldService(obj);
      }
    }
    return intr;
  }
  IHelloWorldService::IHelloWorldService() { printf("IHelloWorldService::IHelloWorldService()\n"); }
  IHelloWorldService::~IHelloWorldService() { printf("IHelloWorldService::~IHelloWorldService()\n"); }
  */
  status_t BnHelloWorldService::onTransact(uint32_t code
					   , const Parcel& data
					   , Parcel* reply
					   , uint32_t flags)
  {
    printf("LOLOLontransact\n");
    switch (code)
    {
      case ALERT:
	alert();
	return NO_ERROR;
      case HELLO:
      {
	CHECK_INTERFACE(IHelloWorldService, data, reply);
	String16 msg = hello();
	reply->writeString16(msg);
	return NO_ERROR;
      } break;
      default:
	return BBinder::onTransact(code, data, reply, flags);
    }
  }

  BpHelloWorldService::BpHelloWorldService(const sp<IBinder>& impl)
    : BpInterface<IHelloWorldService>(impl)
  {
  }

  void BpHelloWorldService::alert()
  {
    Parcel data, reply;
    data.writeInterfaceToken(IHelloWorldService::getInterfaceDescriptor());
    data.writeString16(String16("YO"));
    remote()->transact(BnHelloWorldService::ALERT, data, &reply, IBinder::FLAG_ONEWAY);
  }

  String16 BpHelloWorldService::hello()
  {
    Parcel data, reply;
    data.writeInterfaceToken(IHelloWorldService::getInterfaceDescriptor());
    remote()->transact(BnHelloWorldService::HELLO, data, &reply);

    return reply.readString16();
  }

  String16 HelloWorldService::hello()
  {
    return String16("Hello World !");
  }

  void HelloWorldService::alert()
  {
    printf("YO\n");
  }


}
