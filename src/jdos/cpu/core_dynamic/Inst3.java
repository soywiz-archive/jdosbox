package jdos.cpu.core_dynamic;

import jdos.cpu.*;
import jdos.cpu.core_share.Constants;
import jdos.hardware.Memory;
import jdos.hardware.Pic;
import jdos.hardware.IO;
import jdos.util.IntRef;

public class Inst3 extends Helper {
    final static public class Addd_reg extends Op {
        Reg e;
        Reg g;

        public Addd_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            e.dword(Instructions.ADDD(g.dword(), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class AddEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public AddEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            if ((eaa & 0xFFF) < 0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index >= 0) {
                    Memory.host_writed(index, Instructions.ADDD(g.dword(), Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ADDD(g.dword(), Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class AddGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public AddGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            e.dword(Instructions.ADDD(Memory.mem_readd(eaa), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class AddEaxId extends Op {
        long i;

        public AddEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            reg_eax.dword(Instructions.ADDD(i, reg_eax.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class Push32ES extends Op {
        public int call() {
            CPU.CPU_Push32(CPU.Segs_ESval);
            return Constants.BR_Normal;
        }
    }

    final static public class Pop32ES extends Op {
        public int call() {
            if (CPU.CPU_PopSegES(true)) return RUNEXCEPTION();
            return Constants.BR_Normal;
        }
    }

    final static public class Ord_reg extends Op {
        Reg e;
        Reg g;

        public Ord_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            e.dword(Instructions.ORD(g.dword(), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class OrEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public OrEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            if ((eaa & 0xFFF) < 0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index >= 0) {
                    Memory.host_writed(index, Instructions.ORD(g.dword(), Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ORD(g.dword(), Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class OrGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public OrGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            e.dword(Instructions.ORD(Memory.mem_readd(eaa), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class OrEaxId extends Op {
        long i;

        public OrEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            reg_eax.dword(Instructions.ORD(i, reg_eax.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class Push32CS extends Op {
        public int call() {
            CPU.CPU_Push32(CPU.Segs_CSval);
            return Constants.BR_Normal;
        }
    }

    final static public class Adcd_reg extends Op {
        Reg e;
        Reg g;

        public Adcd_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            e.dword(Instructions.ADCD(g.dword(), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class AdcEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public AdcEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            if ((eaa & 0xFFF) < 0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index >= 0) {
                    Memory.host_writed(index, Instructions.ADCD(g.dword(), Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ADCD(g.dword(), Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class AdcGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public AdcGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            e.dword(Instructions.ADCD(Memory.mem_readd(eaa), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class AdcEaxId extends Op {
        long i;

        public AdcEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            reg_eax.dword(Instructions.ADCD(i, reg_eax.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class Push32SS extends Op {
        public int call() {
            CPU.CPU_Push32(CPU.Segs_SSval);
            return Constants.BR_Normal;
        }
    }

    final static public class Pop32SS extends Op {
        public int call() {
            if (CPU.CPU_PopSegSS(true)) return RUNEXCEPTION();
            return Constants.BR_Normal;
        }
    }

    final static public class Sbbd_reg extends Op {
        Reg e;
        Reg g;

        public Sbbd_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            e.dword(Instructions.SBBD(g.dword(), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class SbbEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public SbbEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            if ((eaa & 0xFFF) < 0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index >= 0) {
                    Memory.host_writed(index, Instructions.SBBD(g.dword(), Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.SBBD(g.dword(), Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class SbbGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public SbbGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            e.dword(Instructions.SBBD(Memory.mem_readd(eaa), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class SbbEaxId extends Op {
        long i;

        public SbbEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            reg_eax.dword(Instructions.SBBD(i, reg_eax.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class Push32DS extends Op {
        public int call() {
            CPU.CPU_Push32(CPU.Segs_DSval);
            return Constants.BR_Normal;
        }
    }

    final static public class Pop32DS extends Op {
        public int call() {
            if (CPU.CPU_PopSegDS(true)) return RUNEXCEPTION();
            return Constants.BR_Normal;
        }
    }

    final static public class Andd_reg extends Op {
        Reg e;
        Reg g;

        public Andd_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            e.dword(Instructions.ANDD(g.dword(), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class AndEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public AndEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            if ((eaa & 0xFFF) < 0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index >= 0) {
                    Memory.host_writed(index, Instructions.ANDD(g.dword(), Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ANDD(g.dword(), Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class AndGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public AndGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            e.dword(Instructions.ANDD(Memory.mem_readd(eaa), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class AndEaxId extends Op {
        long i;

        public AndEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            reg_eax.dword(Instructions.ANDD(i, reg_eax.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class Subd_reg extends Op {
        Reg e;
        Reg g;

        public Subd_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            e.dword(Instructions.SUBD(g.dword(), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class SubEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public SubEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            if ((eaa & 0xFFF) < 0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index >= 0) {
                    Memory.host_writed(index, Instructions.SUBD(g.dword(), Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.SUBD(g.dword(), Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class SubGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public SubGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            e.dword(Instructions.SUBD(Memory.mem_readd(eaa), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class SubEaxId extends Op {
        long i;

        public SubEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            reg_eax.dword(Instructions.SUBD(i, reg_eax.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class Xord_reg extends Op {
        Reg e;
        Reg g;

        public Xord_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            e.dword(Instructions.XORD(g.dword(), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class XorEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public XorEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            if ((eaa & 0xFFF) < 0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index >= 0) {
                    Memory.host_writed(index, Instructions.XORD(g.dword(), Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.XORD(g.dword(), Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class XorGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public XorGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            e.dword(Instructions.XORD(Memory.mem_readd(eaa), e.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class XorEaxId extends Op {
        long i;

        public XorEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            reg_eax.dword(Instructions.XORD(i, reg_eax.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class Cmpd_reg extends Op {
        Reg e;
        Reg g;

        public Cmpd_reg(Reg e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            Instructions.CMPD(g.dword(), e.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class CmpEdGd_mem extends Op {
        EaaBase e;
        Reg g;

        public CmpEdGd_mem(EaaBase e, Reg g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = e.call();
            Instructions.CMPD(g.dword(), Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }

    final static public class CmpGdEd_mem extends Op {
        EaaBase g;
        Reg e;

        public CmpGdEd_mem(Reg e, EaaBase g) {
            this.e = e;
            this.g = g;
        }

        public int call() {
            long eaa = g.call();
            Instructions.CMPD(Memory.mem_readd(eaa), e.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class CmpEaxId extends Op {
        long i;

        public CmpEaxId() {
            i = decode_fetchd();
        }

        public int call() {
            Instructions.CMPD(i, reg_eax.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class Incd_reg extends Op {
        Reg reg;
        public Incd_reg(Reg reg) {
            this.reg = reg;
        }

        public int call() {
            LoadCF();lflags.var1 = reg.dword();
            lflags.res = (lflags.var1+1) & 0xFFFFFFFFl;
            reg.dword(lflags.res);
            lflags.type=t_INCd;
            return Constants.BR_Normal;
        }
    }

    final static public class Incd_mem extends Op {
        EaaBase get_eaa;
        public Incd_mem(int rm) {
            this.get_eaa = Mod.getEaa(rm);
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.INCD(Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.INCD(Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class Decd_reg extends Op {
        Reg reg;
        public Decd_reg(Reg reg) {
            this.reg = reg;
        }

        public int call() {
            LoadCF();lflags.var1 = reg.dword();
            lflags.res = (lflags.var1-1) & 0xFFFFFFFFl;
            reg.dword(lflags.res);
            lflags.type=t_DECd;
            return Constants.BR_Normal;
        }
    }

    final static public class Decd_mem extends Op {
        EaaBase get_eaa;
        public Decd_mem(int rm) {
            this.get_eaa = Mod.getEaa(rm);
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.DECD(Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.DECD(Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class Push32_reg extends Op {
        Reg reg;
        public Push32_reg(Reg reg) {
            this.reg = reg;
        }

        public int call() {
            CPU.CPU_Push32(reg.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class Pop32_reg extends Op {
        Reg reg;
        public Pop32_reg(Reg reg) {
            this.reg = reg;
        }

        public int call() {
            reg.dword(CPU.CPU_Pop32());
            return Constants.BR_Normal;
        }
    }

    final static public class Pushad extends Op {
        public int call() {
            /*Bitu*/long tmpesp = reg_esp.dword();
            CPU.CPU_Push32(reg_eax.dword());CPU.CPU_Push32(reg_ecx.dword());CPU.CPU_Push32(reg_edx.dword());CPU.CPU_Push32(reg_ebx.dword());
            CPU.CPU_Push32(tmpesp);CPU.CPU_Push32(reg_ebp.dword());CPU.CPU_Push32(reg_esi.dword());CPU.CPU_Push32(reg_edi.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class Popad extends Op {
        public int call() {
            reg_edi.dword(CPU.CPU_Pop32());
            reg_esi.dword(CPU.CPU_Pop32());
            reg_ebp.dword(CPU.CPU_Pop32());CPU.CPU_Pop32();//Don't save ESP
            reg_ebx.dword(CPU.CPU_Pop32());
            reg_edx.dword(CPU.CPU_Pop32());
            reg_ecx.dword(CPU.CPU_Pop32());
            reg_eax.dword(CPU.CPU_Pop32());
            return Constants.BR_Normal;
        }
    }

    final static public class BoundEd extends Op {
        EaaBase get_eaa;
        Reg rd;

        public BoundEd() {
            int rm = decode_fetchb();
            get_eaa =  Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }
        public int call() {
            int bound_min, bound_max;
            long eaa=get_eaa.call();
            bound_min=(int)Memory.mem_readd(eaa);
            bound_max=(int)Memory.mem_readd(eaa+4);
            int rmrd = (int)(rd.dword() & 0xFFFFFFFFl);
            if (rmrd < bound_min || rmrd > bound_max) {
                return EXCEPTION(5);
            }
            return Constants.BR_Normal;
        }
    }

    final static public class ArplEdRd_reg extends Op {
        Reg rd;
        Reg eard;
        IntRef ref = new IntRef(0);

        public ArplEdRd_reg(int rm) {
            rd = Mod.gd(rm);
            eard = Mod.ed(rm);
        }

        public int call() {
            if (((CPU.cpu.pmode) && (CPU_Regs.flags & CPU_Regs.VM)!=0) || (!CPU.cpu.pmode)) return Constants.BR_Illegal;
            ref.value = (int) eard.dword();
            CPU.CPU_ARPL(ref,rd.word());
            eard.dword(ref.value);
            return Constants.BR_Normal;
        }
    }

    final static public class ArplEdRd_mem extends Op {
        EaaBase get_eaa;
        Reg rd;
        IntRef ref = new IntRef(0);

        public ArplEdRd_mem(int rm) {
            get_eaa =  Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }
        public int call() {
            long eaa=get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    ref.value = Memory.host_readw(index);
                    CPU.CPU_ARPL(ref,rd.word());
                    Memory.host_writed(index,ref.value);
                    return Constants.BR_Normal;
                }
            }
            ref.value = Memory.mem_readw(eaa);
            CPU.CPU_ARPL(ref,rd.word());
            Memory.mem_writed(eaa,ref.value);
            return Constants.BR_Normal;
        }
    }

    final static public class PushId extends Op {
        long id;

        public PushId() {
            id = decode_fetchd();
        }
        public int call() {
            CPU.CPU_Push32(id);
            return Constants.BR_Normal;
        }
    }

    final static public class ImulGdEdId_reg extends Op {
        Reg eard;
        Reg rd;
        long op3;

        public ImulGdEdId_reg(int rm) {
            eard = Mod.ed(rm);
            rd = Mod.gd(rm);
            op3 = decode_fetchds();
        }

        public int call() {
            rd.dword(Instructions.DIMULD(eard.dword(),op3));
            return Constants.BR_Normal;
        }
    }

    final static public class ImulGdEdId_mem extends Op {
        EaaBase get_eaa;
        Reg rd;
        long op3;

        public ImulGdEdId_mem(int rm) {
            get_eaa =  Mod.getEaa(rm);
            rd = Mod.gd(rm);
            op3 = decode_fetchds();
        }
        public int call() {
            long eaa = get_eaa.call();
            rd.dword(Instructions.DIMULD(Memory.mem_readd(eaa),op3));
            return Constants.BR_Normal;
        }
    }

    final static public class PushIb extends Op {
        long id;

        public PushIb() {
            id = decode_fetchbs();
        }
        public int call() {
            CPU.CPU_Push32(id);
            return Constants.BR_Normal;
        }
    }

    final static public class ImulGdEdIb_reg extends Op {
        Reg eard;
        Reg rd;
        long op3;

        public ImulGdEdIb_reg(int rm) {
            eard = Mod.ed(rm);
            rd = Mod.gd(rm);
            op3 = decode_fetchbs();
        }

        public int call() {
            rd.dword(Instructions.DIMULD(eard.dword(),op3));
            return Constants.BR_Normal;
        }
    }

    final static public class ImulGdEdIb_mem extends Op {
        EaaBase get_eaa;
        Reg rd;
        long op3;

        public ImulGdEdIb_mem(int rm) {
            get_eaa =  Mod.getEaa(rm);
            rd = Mod.gd(rm);
            op3 = decode_fetchbs();
        }
        public int call() {
            long eaa = get_eaa.call();
            rd.dword(Instructions.DIMULD(Memory.mem_readd(eaa),op3));
            return Constants.BR_Normal;
        }
    }

    static abstract public class JumpCond32_b extends Op {
        int offset;
        long eip;
        public JumpCond32_b() {
            eip = iGETIP();
            offset = decode_fetchbs();
        }
    }

    final static public class JumpCond32_b_o extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_O(), eip, offset);
        }
    }

    final static public class JumpCond32_b_no extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NO(), eip, offset);
        }
    }

    final static public class JumpCond32_b_b extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_B(), eip, offset);
        }
    }

    final static public class JumpCond32_b_nb extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NB(), eip, offset);
        }
    }

    final static public class JumpCond32_b_z extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_Z(), eip, offset);
        }
    }

    final static public class JumpCond32_b_nz extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NZ(), eip, offset);
        }
    }

    final static public class JumpCond32_b_be extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_BE(), eip, offset);
        }
    }

    final static public class JumpCond32_b_nbe extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NBE(), eip, offset);
        }
    }

    final static public class JumpCond32_b_s extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_S(), eip, offset);
        }
    }

    final static public class JumpCond32_b_ns extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NS(), eip, offset);
        }
    }

    final static public class JumpCond32_b_p extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_P(), eip, offset);
        }
    }

    final static public class JumpCond32_b_np extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NP(), eip, offset);
        }
    }

    final static public class JumpCond32_b_l extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_L(), eip, offset);
        }
    }

    final static public class JumpCond32_b_nl extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NL(), eip, offset);
        }
    }

    final static public class JumpCond32_b_le extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_LE(), eip, offset);
        }
    }

    final static public class JumpCond32_b_nle extends JumpCond32_b {
        public int call() {
            return JumpCond32_b(Flags.TFLG_NLE(), eip, offset);
        }
    }

    final static public class GrplEdId_reg_add extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_add(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            eard.dword(Instructions.ADDD(ib,eard.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_reg_or extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_or(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            eard.dword(Instructions.ORD(ib,eard.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_reg_adc extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_adc(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            eard.dword(Instructions.ADCD(ib,eard.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_reg_sbb extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_sbb(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            eard.dword(Instructions.SBBD(ib,eard.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_reg_and extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_and(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            eard.dword(Instructions.ANDD(ib,eard.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_reg_sub extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_sub(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            eard.dword(Instructions.SUBD(ib,eard.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_reg_xor extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_xor(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            eard.dword(Instructions.XORD(ib,eard.dword()));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_reg_cmp extends Op {
        Reg eard;
        long ib;

        public GrplEdId_reg_cmp(int rm, boolean signed) {
            eard = Mod.ed(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            Instructions.CMPD(ib,eard.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_add extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_add(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.ADDD(ib,Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ADDD(ib,Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_or extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_or(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.ORD(ib,Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ORD(ib,Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_adc extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_adc(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.ADCD(ib,Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ADCD(ib,Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_sbb extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_sbb(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.SBBD(ib,Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.SBBD(ib,Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_and extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_and(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.ANDD(ib,Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.ANDD(ib,Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_sub extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_sub(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.SUBD(ib,Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.SUBD(ib,Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_xor extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_xor(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    Memory.host_writed(index, Instructions.XORD(ib,Memory.host_readd(index)));
                    return Constants.BR_Normal;
                }
            }
            Memory.mem_writed(eaa, Instructions.XORD(ib,Memory.mem_readd(eaa)));
            return Constants.BR_Normal;
        }
    }

    final static public class GrplEdId_mem_cmp extends Op {
        long ib;
        EaaBase get_eaa;

        public GrplEdId_mem_cmp(int rm, boolean signed) {
            get_eaa= Mod.getEaa(rm);
            if (signed)
                ib = (((short)decode_fetchbs()) & 0xFFFFFFFFl);
            else
                ib = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            Instructions.CMPD(ib,Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }

    final static public class TestEdGd_reg extends Op {
        Reg eard;
        Reg rd;

        public TestEdGd_reg(int rm) {
            eard = Mod.ed(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            Instructions.TESTD(rd.dword(), eard.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class TestEdGd_mem extends Op {
        EaaBase get_eaa;
        Reg rd;

        public TestEdGd_mem(int rm) {
            get_eaa= Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            long eaa = get_eaa.call();
            Instructions.TESTD(rd.dword(), Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }

    final static public class XchgEdGd_reg extends Op {
        Reg eard;
        Reg rd;

        public XchgEdGd_reg(int rm) {
            eard = Mod.ed(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            long oldrmrd= rd.dword();
            rd.dword(eard.dword());
            eard.dword(oldrmrd);
            return Constants.BR_Normal;
        }
    }

    final static public class XchgEdGd_mem extends Op {
        EaaBase get_eaa;
        Reg rd;

        public XchgEdGd_mem(int rm) {
            get_eaa= Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            long eaa = get_eaa.call();
            long oldrmrd= rd.dword();
             if ((eaa & 0xFFF)<0xFFD) {
                int index = Paging.getDirectIndex(eaa);
                if (index>=0) {
                    rd.dword(Memory.host_readd(index));
                    Memory.host_writed(index,oldrmrd);
                    return Constants.BR_Normal;
                }
            }
            rd.dword(Memory.mem_readd(eaa));
            Memory.mem_writed(eaa,oldrmrd);
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdGd_reg extends Op {
        Reg eard;
        Reg rd;

        public MovEdGd_reg(int rm) {
            eard = Mod.ed(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            eard.dword(rd.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdGd_mem extends Op {
        EaaBase get_eaa;
        Reg rd;

        public MovEdGd_mem(int rm) {
            get_eaa= Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            long eaa = get_eaa.call();
            Memory.mem_writed(eaa, rd.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class MovGdEd_reg extends Op {
        Reg eard;
        Reg rd;

        public MovGdEd_reg(int rm) {
            eard = Mod.ed(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            rd.dword(eard.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class MovGdEd_mem extends Op {
        EaaBase get_eaa;
        Reg rd;

        public MovGdEd_mem(int rm) {
            get_eaa= Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }

        public int call() {
            long eaa = get_eaa.call();
            rd.dword(Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdEs_reg extends Op {
        Reg eard;
        public MovEdEs_reg(int rm) {
            eard = Mod.ed(rm);
        }

        public int call() {
            eard.dword(CPU.Segs_ESval & 0xFFFF); // this dword assignment is intentional
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdCs_reg extends Op {
        Reg eard;
        public MovEdCs_reg(int rm) {
            eard = Mod.ed(rm);
        }

        public int call() {
            eard.dword(CPU.Segs_CSval & 0xFFFF); // this dword assignment is intentional
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdSs_reg extends Op {
        Reg eard;
        public MovEdSs_reg(int rm) {
            eard = Mod.ed(rm);
        }

        public int call() {
            eard.dword(CPU.Segs_SSval & 0xFFFF); // this dword assignment is intentional
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdDs_reg extends Op {
        Reg eard;
        public MovEdDs_reg(int rm) {
            eard = Mod.ed(rm);
        }

        public int call() {
            eard.dword(CPU.Segs_DSval & 0xFFFF); // this dword assignment is intentional
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdFs_reg extends Op {
        Reg eard;
        public MovEdFs_reg(int rm) {
            eard = Mod.ed(rm);
        }

        public int call() {
            eard.dword(CPU.Segs_FSval & 0xFFFF); // this dword assignment is intentional
            return Constants.BR_Normal;
        }
    }

    final static public class MovEdGs_reg extends Op {
        Reg eard;
        public MovEdGs_reg(int rm) {
            eard = Mod.ed(rm);
        }

        public int call() {
            eard.dword(CPU.Segs_GSval & 0xFFFF); // this dword assignment is intentional
            return Constants.BR_Normal;
        }
    }

    final static public class LeaGd_16 extends Op {
        Reg rd;
        EaaBase get_eaa;

        public LeaGd_16(int rm) {
            rd = Mod.gd(rm);
            get_eaa= Mod.getEaa16(rm);
        }
        public int call() {
            //Little hack to always use segprefixed version
            Core.base_ds=Core.base_ss=0;
            long eaa = get_eaa.call();
            rd.dword(eaa);
            return Constants.BR_Normal;
        }
    }

    final static public class LeaGd_32 extends Op {
        Reg rd;
        EaaBase get_eaa;

        public LeaGd_32(int rm) {
            rd = Mod.gd(rm);
            get_eaa= Mod.getEaa32(rm);
        }
        public int call() {
            //Little hack to always use segprefixed version
            Core.base_ds=Core.base_ss=0;
            long eaa = get_eaa.call();
            rd.dword(eaa);
            return Constants.BR_Normal;
        }
    }

    final static public class PopEd_reg extends Op {
        Reg eard;
        public PopEd_reg(int rm) {
            eard = Mod.ed(rm);
        }
        public int call() {
            eard.dword(CPU.CPU_Pop32());
            return Constants.BR_Normal;
        }
    }

    final static public class PopEd_mem extends Op {
        EaaBase get_eaa;
        public PopEd_mem(int rm) {
            get_eaa= Mod.getEaa(rm);
        }
        public int call() {
            long eaa = get_eaa.call();
            Memory.mem_writed(eaa, CPU.CPU_Pop32());
            return Constants.BR_Normal;
        }
    }

    final static public class XchgEax extends Op {
        Reg reg;

        public XchgEax(Reg reg) {
            this.reg = reg;
        }

        public int call() {
            long old=reg.dword();
            reg.dword(reg_eax.dword());
            reg_eax.dword(old);
            return Constants.BR_Normal;
        }
    }

    final static public class Cwde extends Op {
        public int call() {
            reg_eax.dword((short)reg_eax.word());
            return Constants.BR_Normal;
        }
    }

    final static public class Cdq extends Op {
        public int call() {
            if ((reg_eax.dword() & 0x80000000l)!=0) reg_edx.dword(0xffffffffl);
            else reg_edx.dword(0);
            return Constants.BR_Normal;
        }
    }

    final static public class CallFarAp extends Op {
        int newcs;
        long newip;
        long eip;

        public CallFarAp(int newcs, long newip) {
            eip = iGETIP();
            this.newcs = newcs;
            this.newip = newip;
        }

        public int call() {
            Flags.FillFlags();
            CPU.CPU_CALL(true,newcs,newip,eip);
            if (CPU_TRAP_CHECK) {
                if (GETFLAG(TF)!=0) {
                    CPU.cpudecoder= Core_dynamic.CPU_Core_Dynrec_Trap_Run;
                    return Constants.BR_CBRet_None;
                }
            }
            return Constants.BR_Jump;
        }
    }

    final static public class Pushfd extends Op {
        public int call() {
            if (CPU.CPU_PUSHF(true)) return RUNEXCEPTION();
            return Constants.BR_Normal;
        }
    }

    final static public class Popfd extends Op {
        public int call() {
            if (CPU.CPU_POPF(true)) return RUNEXCEPTION();
            if (CPU_TRAP_CHECK) {
                    if (GETFLAG(TF)!=0) {
                        CPU.cpudecoder= Core_dynamic.CPU_Core_Dynrec_Trap_Run;
                        return DECODE_END();
                    }
            }
            if (CPU_PIC_CHECK)
                if (GETFLAG(IF)!=0 && Pic.PIC_IRQCheck!=0) return DECODE_END();
            return Constants.BR_Normal;
        }
    }

     final static public class MovEaxOd extends Inst1.GetEADirect {
        public int call() {
            long eaa = (Core.base_ds+value) & 0xFFFFFFFFl;
            reg_eax.dword(Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }

    final static public class MovOdEax extends Inst1.GetEADirect {
        public int call() {
            long eaa = (Core.base_ds+value) & 0xFFFFFFFFl;
            Memory.mem_writed(eaa, reg_eax.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class TestEaxId extends Op {
        long id;
        public TestEaxId() {
            id = decode_fetchd();
        }
        public int call() {
            Instructions.TESTD(id,reg_eax.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class MovId extends Op {
        long id;
        Reg reg;
        public MovId(Reg reg) {
            id = decode_fetchd();
            this.reg = reg;
        }
        public int call() {
            reg.dword(id);
            return Constants.BR_Normal;
        }
    }

    final static public class MovId_mem extends Op {
        long id;
        EaaBase get_eaa;
        public MovId_mem(int rm) {
            get_eaa= Mod.getEaa(rm);
            id = decode_fetchd();
        }
        public int call() {
            long eaa = get_eaa.call();
            Memory.mem_writed(eaa, id);
            return Constants.BR_Normal;
        }
    }

    final static public class Retn32Iw extends Op {
        int offset;

        public Retn32Iw() {
            offset = decode_fetchw();
        }
        public int call() {
            reg_eip(CPU.CPU_Pop32());
            reg_esp.dword(reg_esp.dword()+offset);
            return Constants.BR_Normal;
        }
    }

    final static public class Retn32 extends Op {
        public int call() {
            reg_eip(CPU.CPU_Pop32());
            return Constants.BR_Normal;
        }
    }

    final static public class Les32 extends Op {
        EaaBase get_eaa;
        Reg rd;
        public Les32(int rm) {
            get_eaa= Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }
        public int call() {
            long eaa=get_eaa.call();
            if (CPU.CPU_SetSegGeneralES(Memory.mem_readw(eaa+4))) return RUNEXCEPTION();
            rd.dword(Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }

    final static public class Lds32 extends Op {
        EaaBase get_eaa;
        Reg rd;
        public Lds32(int rm) {
            get_eaa= Mod.getEaa(rm);
            rd = Mod.gd(rm);
        }
        public int call() {
            long eaa=get_eaa.call();
            if (CPU.CPU_SetSegGeneralDS(Memory.mem_readw(eaa+4))) return RUNEXCEPTION();
            rd.dword(Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }

    final static public class Enter32IwIb extends Op {
        int bytes;
        int level;
        public Enter32IwIb() {
            bytes=decode_fetchw();
            level=decode_fetchb();
        }
        public int call() {
            CPU.CPU_ENTER(true,bytes,level);
            return Constants.BR_Normal;
        }
    }

    final static public class Leave32 extends Op {
        public int call() {
            reg_esp.dword(reg_esp.dword() & CPU.cpu.stack.notmask);
            reg_esp.dword(reg_esp.dword() | (reg_ebp.dword() & CPU.cpu.stack.mask));
            reg_ebp.dword(CPU.CPU_Pop32());
            return Constants.BR_Normal;
        }
    }

    final static public class Retf32Iw extends Op {
        int words;
        long eip;
        public Retf32Iw() {
            words = decode_fetchw();
            eip = iGETIP();
        }
        public int call() {
            Flags.FillFlags();
            CPU.CPU_RET(true,words,eip);
            return Constants.BR_Jump;
        }
    }

    final static public class Retf32 extends Op {
        long eip;
        public Retf32() {
            eip = iGETIP();
        }
        public int call() {
            Flags.FillFlags();
            CPU.CPU_RET(true,0,eip);
            return Constants.BR_Jump;
        }
    }

    final static public class IRet32 extends Op {
        long eip;
        public IRet32() {
            eip = iGETIP();
        }
        public int call() {
            CPU.CPU_IRET(true, eip);
            if (CPU_TRAP_CHECK) {
                if (GETFLAG(TF)!=0) {
                    CPU.cpudecoder= Core_dynamic.CPU_Core_Dynrec_Trap_Run;
                    return Constants.BR_CBRet_None;
                }
            }
            if (CPU_PIC_CHECK)
                if (GETFLAG(IF)!=0 && Pic.PIC_IRQCheck!=0) return Constants.BR_CBRet_None;
            return Constants.BR_Jump;
        }
    }

    final static public class Loopnz32 extends JumpCond32_b {
        public int call() {
            reg_ecx.dword(reg_ecx.dword()-1);
            return JumpCond32_b(reg_ecx.dword()!=0 && !Flags.get_ZF(), eip, offset);
        }
    }

    final static public class Loopnz16 extends JumpCond32_b {
        public int call() {
            reg_ecx.word(reg_ecx.word()-1);
            return JumpCond32_b(reg_ecx.word()!=0 && !Flags.get_ZF(), eip, offset);
        }
    }

    final static public class Loopz32 extends JumpCond32_b {
        public int call() {
            reg_ecx.dword(reg_ecx.dword()-1);
            return JumpCond32_b(reg_ecx.dword()!=0 && Flags.get_ZF(), eip, offset);
        }
    }

    final static public class Loopz16 extends JumpCond32_b {
        public int call() {
            reg_ecx.word(reg_ecx.word()-1);
            return JumpCond32_b(reg_ecx.word()!=0 && Flags.get_ZF(), eip, offset);
        }
    }

    final static public class Loop32 extends JumpCond32_b {
        public int call() {
            reg_ecx.dword(reg_ecx.dword()-1);
            return JumpCond32_b(reg_ecx.dword()!=0, eip, offset);
        }
    }

    final static public class Loop16 extends JumpCond32_b {
        public int call() {
            reg_ecx.word(reg_ecx.word()-1);
            return JumpCond32_b(reg_ecx.word()!=0, eip, offset);
        }
    }

    final static public class Jcxz extends JumpCond32_b {
        long mask;
        public Jcxz(long mask) {
            this.mask = mask;
        }
        public int call() {
            return JumpCond32_b((reg_ecx.dword() & mask)==0, eip, offset);
        }
    }

    final static public class InEaxIb extends Op {
        int port;
        public InEaxIb() {
            port=decode_fetchb();
        }
        public int call() {
            if (CPU.CPU_IO_Exception(port,4)) return RUNEXCEPTION();
            reg_eax.dword(IO.IO_ReadD(port));
            return Constants.BR_Normal;
        }
    }

    final static public class OutEaxIb extends Op {
        int port;
        public OutEaxIb() {
            port=decode_fetchb();
        }
        public int call() {
            if (CPU.CPU_IO_Exception(port,4)) return RUNEXCEPTION();
            IO.IO_WriteD(port,reg_eax.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class CallJd extends Op {
        long addip;
        long eip;
        public CallJd() {
            addip=decode_fetchds();
            eip = iGETIP();
        }
        public int call() {
            reg_eip(eip);
            CPU.CPU_Push32(reg_eip());
            reg_eip(reg_eip()+addip);
            return Constants.BR_Jump;
        }
    }

    final static public class JmpJd extends Op {
        long addip;
        long eip;
        public JmpJd() {
            addip=decode_fetchds();
            eip = iGETIP();
        }
        public int call() {
            reg_eip(eip);
            reg_eip(reg_eip()+addip);
            return Constants.BR_Jump;
        }
    }

    final static public class JmpAd extends Op {
        long newip;
        int newcs;
        long eip;
        public JmpAd() {
            newip=decode_fetchd();
            newcs=decode_fetchw();
            eip = iGETIP();
        }
        public int call() {
            Flags.FillFlags();
            CPU.CPU_JMP(true,newcs,(int)newip,eip);
            if (CPU_TRAP_CHECK) {
                if (GETFLAG(TF)!=0) {
                    CPU.cpudecoder= Core_dynamic.CPU_Core_Dynrec_Trap_Run;
                    return Constants.BR_CBRet_None;
                }
            }
            return Constants.BR_Jump;
        }
    }

    final static public class JmpJb extends Op {
        int addip;
        long eip;
        public JmpJb() {
            addip=decode_fetchbs();
            eip = iGETIP();
        }
        public int call() {
            reg_eip(eip);
            reg_eip(reg_eip()+addip);
            return Constants.BR_Jump;
        }
    }

    final static public class InEaxDx extends Op {
        public int call() {
            reg_eax.dword(IO.IO_ReadD(reg_edx.word()));
            return Constants.BR_Normal;
        }
    }

    final static public class OutEaxDx extends Op {
        public int call() {
            IO.IO_WriteD(reg_edx.word(),reg_eax.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class CallNearEd_reg extends Op {
        Reg eard;
        long eip;

        public CallNearEd_reg(int rm) {
            eard = Mod.ed(rm);
            eip = iGETIP();
        }

        public int call() {
            reg_eip(eard.dword());
            CPU.CPU_Push32(eip);
            return Constants.BR_Jump;
        }
    }

    final static public class CallNearEd_mem extends Op {
        EaaBase get_eaa;
        long eip;

        public CallNearEd_mem(int rm) {
            get_eaa =  Mod.getEaa(rm);
            eip = iGETIP();
        }
        public int call() {
            long eaa=get_eaa.call();
            reg_eip(Memory.mem_readd(eaa));
            CPU.CPU_Push32(eip);
            return Constants.BR_Jump;
        }
    }

    final static public class CallFarEd_mem extends Op {
        EaaBase get_eaa;
        long eip;

        public CallFarEd_mem(int rm) {
            get_eaa =  Mod.getEaa(rm);
            eip = iGETIP();
        }
        public int call() {
            long eaa=get_eaa.call();
            long newip=Memory.mem_readd(eaa);
            int newcs=Memory.mem_readw(eaa+4);
            FillFlags();
            CPU.CPU_CALL(true,newcs,newip,eip);
            if (CPU_TRAP_CHECK) {
                if (GETFLAG(TF)!=0) {
                    CPU.cpudecoder= Core_dynamic.CPU_Core_Dynrec_Trap_Run;
                    return Constants.BR_CBRet_None;
                }
            }
            return Constants.BR_Jump;
        }
    }

    final static public class JmpNearEd_reg extends Op {
        Reg eard;

        public JmpNearEd_reg(int rm) {
            eard = Mod.ed(rm);
        }

        public int call() {
            reg_eip(eard.dword());
            return Constants.BR_Jump;
        }
    }

    final static public class JmpNearEd_mem extends Op {
        EaaBase get_eaa;

        public JmpNearEd_mem(int rm) {
            get_eaa =  Mod.getEaa(rm);
        }
        public int call() {
            long eaa=get_eaa.call();
            reg_eip(Memory.mem_readd(eaa));
            return Constants.BR_Jump;
        }
    }

    final static public class JmpFarEd_mem extends Op {
        EaaBase get_eaa;
        long eip;

        public JmpFarEd_mem(int rm) {
            get_eaa =  Mod.getEaa(rm);
            eip = iGETIP();
        }
        public int call() {
            long eaa=get_eaa.call();
            long newip=Memory.mem_readd(eaa);
            int newcs=Memory.mem_readw(eaa+4);
            FillFlags();
            CPU.CPU_JMP(true,newcs,(int)newip,eip);
            if (CPU_TRAP_CHECK) {
                if (GETFLAG(TF)!=0) {
                    CPU.cpudecoder= Core_dynamic.CPU_Core_Dynrec_Trap_Run;
                    return Constants.BR_CBRet_None;
                }
            }
            return Constants.BR_Jump;
        }
    }

    final static public class PushEd_reg extends Op {
        Reg eard;
        public PushEd_reg(int rm) {
            eard = Mod.ed(rm);
        }
        public int call() {
            CPU.CPU_Push32(eard.dword());
            return Constants.BR_Normal;
        }
    }

    final static public class PushEd_mem extends Op {
        EaaBase get_eaa;
        public PushEd_mem(int rm) {
            this.get_eaa = Mod.getEaa(rm);
        }
        public int call() {
            long eaa = get_eaa.call();
            CPU.CPU_Push32(Memory.mem_readd(eaa));
            return Constants.BR_Normal;
        }
    }
}